package com.ankit.repository;

import com.ankit.entity.ServiceMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceMetricsRepository extends JpaRepository<ServiceMetrics, Long> {
    Optional<ServiceMetrics> findByServiceInstanceId(Long serviceInstanceId);
}
