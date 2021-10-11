package com.inv.service.impl;

import com.inv.entities.CompanyReportEntity;
import com.inv.repositories.CompanyReportRepository;
import com.inv.service.JasperService;
import com.inv.service.ProcessReportRequestService;
import com.inv.service.dto.FormReportDto;
import com.inv.service.dto.FormReportPriorityDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@AllArgsConstructor
@Scope("singleton") //TODO create tests
public class ProcessReportRequestServiceImpl implements ProcessReportRequestService {

    private final ArrayDeque<FormReportDto> companyReportQueue = new ArrayDeque<>();
    private final PriorityQueue<FormReportPriorityDto> companyReportPriorityQueue =
            new PriorityQueue<>(Comparator.comparing(FormReportPriorityDto::getPriority));

    private final JasperService jasperService;
    private final CompanyReportRepository companyReportRepository;

    private final ExecutorService processReportRequestPool = Executors.newFixedThreadPool(1);
    private final ExecutorService processReportPriorityRequestPool = Executors.newFixedThreadPool(1);

    @Override
    public void addCompanyReportRequest(FormReportDto formReportDto) {
        log.debug("Start sending a request for company report into a queue. Company: {}", formReportDto.getCompany());

        boolean isSuccess = companyReportQueue.add(formReportDto);

        log.debug("Finish sending a request for company report into a queue. Company: {}, success: {}",
                formReportDto.getCompany(),
                isSuccess);
    }

    //    @Scheduled(cron = "${process-company-report-request.schedule}")//TODO check env
    @Scheduled(cron = "5 * * * * *")
    public void createCompanyReport() {
        processReportRequestPool.execute(() -> {

            if (companyReportQueue.isEmpty()) {
                log.info("The queue is empty, next poll will start in 5 minutes");
            } else {

                FormReportDto currentRequest = companyReportQueue.poll();

                if (Objects.nonNull(currentRequest)) {
                    this.createAndSafeReport(currentRequest);
                }
            }
        });
    }

    private void createAndSafeReport(FormReportDto currentRequest) {
        log.debug("Got a request from queue for company: {}", currentRequest.getCompany());

        byte[] report = this.jasperService.createCompanyReport(currentRequest);

        CompanyReportEntity companyReportEntity = new CompanyReportEntity();
        companyReportEntity.setCompanyName(currentRequest.getCompany());
        companyReportEntity.setReport(report);

        CompanyReportEntity saved = this.companyReportRepository.save(companyReportEntity);

        log.debug("Report has been saved: id: {}, company: {}", saved.getId(), saved.getCompanyName());
    }

    @Override
    public void addCompanyReportPriorityRequest(FormReportPriorityDto reportPriorityDto) {
        log.debug("Start sending a request for company report into a queue with priority: {}", reportPriorityDto);

        boolean isSuccess = companyReportPriorityQueue.add(reportPriorityDto);

        log.debug("Finish sending a request for company report into a queue (with priority): {}, success: {}",
                reportPriorityDto,
                isSuccess);
    }

    @Override
    @Scheduled(cron = "5 * * * * *") //TODO add env
    public void createCompanyPriorityReport() {
        processReportPriorityRequestPool.execute(() -> {

            if (companyReportPriorityQueue.isEmpty()) {
                log.info("The priority queue is empty, next poll will start in 5 minutes");
            } else {

                FormReportPriorityDto currentRequest = companyReportPriorityQueue.poll();

                if (Objects.nonNull(currentRequest)) {
                    FormReportDto formReportDto = new FormReportDto(currentRequest.getCompany());
                    this.createAndSafeReport(formReportDto);
                }
            }
        });
    }
}
