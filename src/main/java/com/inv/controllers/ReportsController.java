package com.inv.controllers;

import com.inv.dto.request.GetReportPriorityRequestDto;
import com.inv.dto.request.GetReportRequestDto;
import com.inv.service.ReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportsController {

    private final ReportService reportService;

    @PostMapping("/company-report")
    @ApiOperation(value = "Запрос на формирование отчёта по компании")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<Void> createCompanyReport(@RequestBody GetReportRequestDto requestDto) {
        reportService.formCompanyReport(requestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/company-priority-report")
    @ApiOperation(value = "Запрос на формирование отчёта по компании, с учётом приоритета пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<Void> createCompanyReportWithPriority(@RequestBody GetReportPriorityRequestDto requestDto) {
        reportService.formCompanyReportWithPriority(requestDto);
        return ResponseEntity.ok().build();
    }
}
