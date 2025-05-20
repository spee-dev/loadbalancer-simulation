package com.ankit.controller;

import com.ankit.dto.MetricsResponseDto;
import com.ankit.service.ServiceMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ServiceMetricsService metricsService;

    @GetMapping("/metrics/{serviceName}")
    public ResponseEntity<MetricsResponseDto> getMetrics(@PathVariable String serviceName) {
        // Aggregate metrics logic here
        // Dummy response for illustration:
        MetricsResponseDto dto = new MetricsResponseDto(serviceName, 1000, 15);
        return ResponseEntity.ok(dto);
    }
}
