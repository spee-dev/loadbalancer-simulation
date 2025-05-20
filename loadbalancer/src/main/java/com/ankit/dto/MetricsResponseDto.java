package com.ankit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetricsResponseDto {
    private String serviceName;
    private long totalRequests;
    private long totalErrors;
}
