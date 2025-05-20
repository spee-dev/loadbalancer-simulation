package com.ankit.stratgey;

import com.ankit.entity.ServiceInstance;

import java.util.List;

public interface LoadBalancingStrategy {
    ServiceInstance selectInstance(List<ServiceInstance> instances);
}
