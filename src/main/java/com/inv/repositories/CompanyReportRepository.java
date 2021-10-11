package com.inv.repositories;

import com.inv.entities.CompanyReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

/**
 * {@link CompanyReportEntity} JPA repository.
 */
public interface CompanyReportRepository extends JpaRepository<CompanyReportEntity, UUID>, JpaSpecificationExecutor<CompanyReportEntity> {

}

