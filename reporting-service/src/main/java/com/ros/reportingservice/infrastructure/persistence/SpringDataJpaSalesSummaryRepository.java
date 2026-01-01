package com.ros.reportingservice.infrastructure.persistence;

import com.ros.reportingservice.domain.model.SalesSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaSalesSummaryRepository extends JpaRepository<SalesSummary, Long> {
}