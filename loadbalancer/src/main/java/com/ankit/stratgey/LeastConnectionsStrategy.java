package com.ankit.stratgey;

import com.ankit.entity.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("leastConnectionsStrategy")
public class LeastConnectionsStrategy implements LoadBalancingStrategy {
    @Override
    public ServiceInstance selectInstance(List<ServiceInstance> instances) {
        return instances.stream()
                .min(Comparator.comparingInt(ServiceInstance::getActiveConnections))
                .orElse(null);
    }
}
