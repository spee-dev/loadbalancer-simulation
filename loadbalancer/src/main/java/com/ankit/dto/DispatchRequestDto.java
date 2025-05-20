package com.ankit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DispatchRequestDto {
    @NotBlank
    private String serviceName;
    private String requestId;
}
