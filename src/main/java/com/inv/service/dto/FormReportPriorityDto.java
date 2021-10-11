package com.inv.service.dto;

import com.inv.dto.Company;
import lombok.Data;

@Data
public class FormReportPriorityDto {
    private Company company;
    private int priority;
}

