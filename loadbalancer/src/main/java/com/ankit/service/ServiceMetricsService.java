package com.ankit.service;

import com.ankit.entity.ServiceMetrics;
import com.ankit.repository.ServiceMetricsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceMetricsService {
    private final ServiceMetricsRepository repository;

    @Transactional
    public void incrementRequestCount(Long instanceId) {
        ServiceMetrics metrics = repository.findByServiceInstanceId(instanceId)
            .orElse(new ServiceMetrics(null, instanceId, 0, 0, System.currentTimeMillis()));
        metrics.setRequestCount(metrics.getRequestCount() + 1);
        metrics.setLastUpdated(System.currentTimeMillis());
        repository.save(metrics);
    }
}
