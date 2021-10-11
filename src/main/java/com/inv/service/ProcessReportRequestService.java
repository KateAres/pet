package com.inv.service;

import com.inv.service.dto.FormReportDto;
import com.inv.service.dto.FormReportPriorityDto;

public interface ProcessReportRequestService {

    /**
     * Send report request into the queue.
     *
     * @param formReportDto {@link FormReportDto}
     */
    void addCompanyReportRequest(FormReportDto formReportDto);

    /**
     * Form company report.
     */
    void createCompanyReport();

    /**
     * Send report request into the queue (with priority).
     *
     * @param reportPriorityDto {@link FormReportPriorityDto}
     */
    void addCompanyReportPriorityRequest(FormReportPriorityDto reportPriorityDto);

    /**
     * Form company report (with priority).
     */
    void createCompanyPriorityReport();
}
