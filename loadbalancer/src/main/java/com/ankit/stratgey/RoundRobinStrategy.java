package com.ankit.stratgey;

import com.ankit.entity.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component("roundRobinStrategy")
public class RoundRobinStrategy implements LoadBalancingStrategy {
    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public ServiceInstance selectInstance(List<ServiceInstance> instances) {
        if (instances.isEmpty()) return null;
        int pos = Math.abs(index.getAndIncrement() % instances.size());
        return instances.get(pos);
    }
}
