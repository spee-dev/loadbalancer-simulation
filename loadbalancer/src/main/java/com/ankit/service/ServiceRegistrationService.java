package com.ankit.service;

import com.ankit.dto.ServiceRegistrationDto;
import com.ankit.entity.ServiceInstance;
import com.ankit.repository.ServiceInstanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceRegistrationService {
    private final ServiceInstanceRepository repository;

    public ServiceInstance register(ServiceRegistrationDto dto) {
        ServiceInstance instance = new ServiceInstance();
        BeanUtils.copyProperties(dto, instance);
        instance.setHealthy(true);
        instance.setDecommissioned(false);
        instance.setActiveConnections(0);
        return repository.save(instance);
    }
}
