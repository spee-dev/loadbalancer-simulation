package com.ankit.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceRegistrationDto {
    @NotBlank
    private String serviceName;
    @NotBlank
    private String ipAddress;
    @Min(1)
    private int port;
    private int weight = 1;
    private String metadata;
}
