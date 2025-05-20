package com.ankit.service;

import com.ankit.entity.ServiceInstance;
import com.ankit.repository.ServiceInstanceRepository;
import com.ankit.stratgey.LoadBalancingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ankit.exception.ServiceUnavailableException;

import java.util.List;
import java.util.Map;
import com.ankit.exception.ServiceUnavailableException;
@Service
@RequiredArgsConstructor
public class LoadBalancerService {
    private final ServiceInstanceRepository repository;
    private final Map<String, LoadBalancingStrategy> strategies;
    private final ServiceMetricsService metricsService;

    public ServiceInstance dispatchRequest(String serviceName, String strategyName) {
        List<ServiceInstance> instances = repository.findByServiceNameAndHealthyTrueAndDecommissionedFalse(serviceName);
        if (instances.isEmpty()) throw new ServiceUnavailableException("No healthy instances");

        LoadBalancingStrategy strategy = strategies.getOrDefault(strategyName, strategies.get("roundRobinStrategy"));
        ServiceInstance selected = strategy.selectInstance(instances);

        selected.setActiveConnections(selected.getActiveConnections() + 1);
        repository.save(selected);

        metricsService.incrementRequestCount(selected.getId());

        return selected;
    }

    public void releaseConnection(Long instanceId) {
        repository.findById(instanceId).ifPresent(instance -> {
            instance.setActiveConnections(Math.max(0, instance.getActiveConnections() - 1));
            repository.save(instance);
        });
    }
}
