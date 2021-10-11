package com.inv.service.impl;

import com.inv.service.JasperService;
import com.inv.service.dto.FormReportDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JasperServiceImpl implements JasperService {
    @Override
    public byte[] createCompanyReport(FormReportDto formReportDto) {
        return new byte[0];
    }
}
