package com.inv.dto.request;

import com.inv.dto.Company;
import com.inv.dto.Tariff;
import lombok.Data;

@Data
public class GetReportPriorityRequestDto {
    private Company company;
    private Tariff tariff;
}
