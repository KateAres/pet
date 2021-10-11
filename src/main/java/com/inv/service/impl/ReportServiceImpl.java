package com.inv.service.impl;

import com.inv.dto.request.GetReportPriorityRequestDto;
import com.inv.dto.request.GetReportRequestDto;
import com.inv.service.ProcessReportRequestService;
import com.inv.service.ReportService;
import com.inv.service.dto.FormReportDto;
import com.inv.service.dto.FormReportPriorityDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ProcessReportRequestService processReportRequestService;

    @Override
    public void formCompanyReport(GetReportRequestDto reportRequestDto) {
        log.debug("Start sending request to form company report, for company: {}", reportRequestDto.getCompany());

        FormReportDto formReportDto = new FormReportDto();
        formReportDto.setCompany(reportRequestDto.getCompany());

        processReportRequestService.addCompanyReportRequest(formReportDto);

        log.debug("Finish sending request to form company report, for company: {}", reportRequestDto.getCompany());
    }

    @Override
    public void formCompanyReportWithPriority(GetReportPriorityRequestDto reportRequestDto) {
        log.debug("Start sending request to form company report: {}", reportRequestDto);

        FormReportPriorityDto formReportPriorityDto = new FormReportPriorityDto();
        formReportPriorityDto.setCompany(reportRequestDto.getCompany());
        formReportPriorityDto.setPriority(reportRequestDto.getTariff().getPriority());


        log.debug("Finish sending request to form company report: {}", reportRequestDto);

    }
}
