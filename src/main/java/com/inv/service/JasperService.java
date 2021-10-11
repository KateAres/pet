package com.inv.service;

import com.inv.service.dto.FormReportDto;

public interface JasperService {
    /**
     * Create company report using given data.
     *
     * @param formReportDto {@link FormReportDto}
     * @return report
     */
    byte[] createCompanyReport(FormReportDto formReportDto);
}
