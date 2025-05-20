package com.ankit.controller;
import com.ankit.entity.RequestTrace;
import com.ankit.dto.DispatchRequestDto;
import com.ankit.entity.ServiceInstance;
import com.ankit.service.LoadBalancerService;
import com.ankit.service.TracingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loadbalancer")
@RequiredArgsConstructor
public class DispatchController {
    private final LoadBalancerService loadBalancerService;
    private final TracingService tracingService;

    @PostMapping("/dispatch")
    public ResponseEntity<ServiceInstance> dispatch(@Valid @RequestBody DispatchRequestDto dto,
                                                    @RequestParam(defaultValue = "roundRobinStrategy") String strategy) {
        long startTime = System.currentTimeMillis();

        ServiceInstance instance = loadBalancerService.dispatchRequest(dto.getServiceName(), strategy);

        long latency = System.currentTimeMillis() - startTime;

        tracingService.logRequest(RequestTrace.builder()
                .serviceName(dto.getServiceName())
                .serviceInstanceId(instance.getId())
                .requestId(dto.getRequestId())
                .timestamp(System.currentTimeMillis())
                .latencyMs(latency)
                .status("SUCCESS")
                .build());

        return ResponseEntity.ok(instance);
    }
}
