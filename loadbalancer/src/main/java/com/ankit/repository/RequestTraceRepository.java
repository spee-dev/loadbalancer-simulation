package com.ankit.repository;

import com.ankit.entity.RequestTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestTraceRepository extends JpaRepository<RequestTrace, Long> {
    List<RequestTrace> findByServiceName(String serviceName);
}
