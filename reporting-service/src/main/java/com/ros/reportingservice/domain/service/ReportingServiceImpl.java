package com.ros.reportingservice.domain.service;

import com.ros.reportingservice.application.dto.OrderCreatedEventDto;
import com.ros.reportingservice.domain.model.SalesSummary;
import com.ros.reportingservice.domain.repository.SalesSummaryRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportingServiceImpl implements ReportingService {

    private static final Logger log = LoggerFactory.getLogger(ReportingServiceImpl.class);
    private final SalesSummaryRepository repository;

    @Override
    @Transactional
    public void processNewOrder(OrderCreatedEventDto event) {
        log.info("Processing new order for reporting, orderId: {}", event.getOrderId());

        SalesSummary summary = new SalesSummary(
                event.getOrderId(),
                event.getRestaurantId(),
                event.getCustomerId(),
                event.getTotalPrice(),
                event.getCreatedAt()
        );

        repository.save(summary);

        log.info("Saved new sales summary entry for orderId: {}", event.getOrderId());
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] generateSimpleSalesReport() throws Exception {
        log.info("Generating simple sales report...");

        List<SalesSummary> salesData = repository.findAll();

        File file = ResourceUtils.getFile("classpath:reports/sales_summary_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(salesData);
        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        log.info("Report generation complete.");
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}