package com.ros.reportingservice.application.controller;

import com.ros.reportingservice.domain.service.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportingService reportingService;

    @GetMapping("/sales/simple")
    public ResponseEntity<byte[]> getSimpleSalesReport() {
        try {
            byte[] reportPdf = reportingService.generateSimpleSalesReport();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "SatisRaporu.pdf");
            headers.setContentLength(reportPdf.length);

            return new ResponseEntity<>(reportPdf, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}