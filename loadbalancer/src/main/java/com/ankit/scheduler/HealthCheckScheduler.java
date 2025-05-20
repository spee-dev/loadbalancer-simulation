package com.ankit.scheduler;

import com.ankit.entity.ServiceInstance;
import com.ankit.repository.ServiceInstanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class HealthCheckScheduler {
    private final ServiceInstanceRepository repository;

    @Scheduled(fixedDelay = 30000)
    public void performHealthCheck() {
        List<ServiceInstance> instances = repository.findAll();
        for (ServiceInstance instance : instances) {
            boolean healthy = ping(instance);
            if (instance.isHealthy() != healthy) {
                instance.setHealthy(healthy);
                repository.save(instance);
                log.info("Health status changed for {}: {}", instance.getServiceName(), healthy);
            }
        }
    }

    private boolean ping(ServiceInstance instance) {
        // Implement HTTP ping or TCP ping logic here.
        return true; // stub for demo
    }
}
