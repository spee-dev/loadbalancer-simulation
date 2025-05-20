package com.ankit.stratgey;

import com.ankit.entity.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component("weightedStrategy")
public class WeightedStrategy implements LoadBalancingStrategy {
    private final Random random = new Random();

    @Override
    public ServiceInstance selectInstance(List<ServiceInstance> instances) {
        int totalWeight = instances.stream().mapToInt(ServiceInstance::getWeight).sum();
        int rand = random.nextInt(totalWeight);
        int cumulative = 0;
        for (ServiceInstance instance : instances) {
            cumulative += instance.getWeight();
            if (rand < cumulative) {
                return instance;
            }
        }
        return instances.get(0);
    }
}
