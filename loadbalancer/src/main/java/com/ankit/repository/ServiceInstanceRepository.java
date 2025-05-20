package com.ankit.repository;

import com.ankit.entity.ServiceInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ServiceInstanceRepository extends JpaRepository<ServiceInstance, Long> {
    List<ServiceInstance> findByServiceNameAndHealthyTrueAndDecommissionedFalse(String serviceName);
}
