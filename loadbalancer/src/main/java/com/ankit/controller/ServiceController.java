package com.ankit.controller;

import com.ankit.dto.ServiceRegistrationDto;
import com.ankit.entity.ServiceInstance;
import com.ankit.service.ServiceRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceRegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<ServiceInstance> register(@Valid @RequestBody ServiceRegistrationDto dto) {
        ServiceInstance instance = registrationService.register(dto);
        return ResponseEntity.ok(instance);
    }
}
