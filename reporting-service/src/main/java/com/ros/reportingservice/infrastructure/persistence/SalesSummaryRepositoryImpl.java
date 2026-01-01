package com.ros.reportingservice.infrastructure.persistence;

import com.ros.reportingservice.domain.model.SalesSummary;
import com.ros.reportingservice.domain.repository.SalesSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SalesSummaryRepositoryImpl implements SalesSummaryRepository {

    private final SpringDataJpaSalesSummaryRepository jpaRepository;

    @Override
    public SalesSummary save(SalesSummary summary) {
        return jpaRepository.save(summary);
    }

    @Override
    public List<SalesSummary> findAll() {
        return jpaRepository.findAll();
    }
}