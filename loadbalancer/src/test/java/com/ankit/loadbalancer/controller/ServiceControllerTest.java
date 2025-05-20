package com.ankit.loadbalancer.controller;

import com.ankit.controller.ServiceController;
import com.ankit.dto.ServiceRegistrationDto;
import com.ankit.entity.ServiceInstance;
import com.ankit.service.ServiceRegistrationService;
import com.ankit.service.ServiceRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServiceController.class)
public class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceRegistrationService serviceRegistry;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterService_Success() throws Exception {
        ServiceRegistrationDto dto = new ServiceRegistrationDto();
        dto.setServiceName("test-service");
        dto.setIpAddress("192.168.1.1");
        dto.setPort(8080);

        ServiceInstance mockInstance = new ServiceInstance();
        mockInstance.setId(1L);
        mockInstance.setServiceName("test-service");
        mockInstance.setIpAddress("192.168.1.1");
        mockInstance.setPort(8080);

        when(serviceRegistry.register(dto)).thenReturn(mockInstance);

        mockMvc.perform(post("/api/services/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.serviceName").value("test-service"));
    }

    @Test
    public void testRegisterService_MissingFields_BadRequest() throws Exception {
        ServiceRegistrationDto dto = new ServiceRegistrationDto(); // Missing fields

        mockMvc.perform(post("/api/services/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

    }
}
