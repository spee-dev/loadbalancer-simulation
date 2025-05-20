package com.ankit.scheduler;

import com.ankit.repository.ServiceMetricsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MetricsCleanupScheduler {
    private final ServiceMetricsRepository metricsRepository;

    @Scheduled(cron = "0 0 3 * * ?")  // Every day at 3 AM
    public void cleanupOldMetrics() {
        // Logic to delete metrics older than X days
        log.info("Cleaning up old metrics...");
    }
}
