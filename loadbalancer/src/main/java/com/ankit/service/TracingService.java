package com.ankit.service;

import com.ankit.entity.RequestTrace;
import com.ankit.repository.RequestTraceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TracingService {
    private final RequestTraceRepository repository;

    public void logRequest(RequestTrace trace) {
        repository.save(trace);
    }
}
