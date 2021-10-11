package com.inv.service;

import com.inv.dto.request.GetReportPriorityRequestDto;
import com.inv.dto.request.GetReportRequestDto;

public interface ReportService {
    /**
     * Form company report.
     *
     * @param reportRequestDto {@link GetReportRequestDto}
     */
    void formCompanyReport(GetReportRequestDto reportRequestDto);

    /**
     * Form company report with priority
     *
     * @param reportRequestDto {@link GetReportPriorityRequestDto}
     */
    void formCompanyReportWithPriority(GetReportPriorityRequestDto reportRequestDto);
}
