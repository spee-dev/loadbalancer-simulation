package com.ankit.loadbalancer.controller;

import com.ankit.controller.DispatchController;
import com.ankit.dto.DispatchRequestDto;
import com.ankit.entity.ServiceInstance;
import com.ankit.service.LoadBalancerService;
import com.ankit.service.TracingService;
import com.ankit.entity.RequestTrace;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DispatchController.class)
public class DispatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoadBalancerService loadBalancerService;

    @MockBean
    private TracingService tracingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testDispatch_Success() throws Exception {
        // Prepare mock service instance response
        ServiceInstance instance = new ServiceInstance();
        instance.setId(1L);
        instance.setServiceName("testService");
        instance.setIpAddress("127.0.0.1");
        instance.setPort(8080);

        // Mock LoadBalancerService behavior
        Mockito.when(loadBalancerService.dispatchRequest(anyString(), anyString()))
                .thenReturn(instance);

        // Prepare request DTO JSON
        DispatchRequestDto dto = new DispatchRequestDto();
        dto.setServiceName("testService");
        dto.setRequestId("req-123");

        // Perform POST request and validate
        mockMvc.perform(post("/api/loadbalancer/dispatch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .param("strategy", "roundRobinStrategy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.serviceName").value("testService"))
                .andExpect(jsonPath("$.ipAddress").value("127.0.0.1"))
                .andExpect(jsonPath("$.port").value(8080));

        // Verify tracingService.logRequest was called (optional)
        Mockito.verify(tracingService).logRequest(any(RequestTrace.class));
    }

    @Test
    void testDispatch_MissingServiceName_BadRequest() throws Exception {
        DispatchRequestDto dto = new DispatchRequestDto();
        dto.setRequestId("req-123");
        // Missing serviceName

        mockMvc.perform(post("/api/loadbalancer/dispatch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
