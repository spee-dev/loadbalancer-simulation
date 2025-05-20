package com.ankit.loadbalancer.service;

import com.ankit.entity.ServiceInstance;
import com.ankit.repository.ServiceInstanceRepository;
import com.ankit.service.LoadBalancerService;
import com.ankit.service.ServiceMetricsService;
import com.ankit.stratgey.LoadBalancingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoadBalancerServiceTest {

    @Mock
    private ServiceInstanceRepository repository;

    @Mock
    private Map<String, LoadBalancingStrategy> strategies;

    @Mock
    private ServiceMetricsService metricsService;

    @InjectMocks
    private LoadBalancerService loadBalancerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDispatchRequest_returnsServiceInstance() throws Exception {
        ServiceInstance instance = new ServiceInstance();
        instance.setId(1L);
        instance.setActiveConnections(0);

        List<ServiceInstance> instances = List.of(instance);

        when(repository.findByServiceNameAndHealthyTrueAndDecommissionedFalse("myservice"))
                .thenReturn(instances);

        LoadBalancingStrategy strategy = mock(LoadBalancingStrategy.class);
        when(strategies.getOrDefault(anyString(), any())).thenReturn(strategy);
        when(strategy.selectInstance(instances)).thenReturn(instance);

        ServiceInstance selected = loadBalancerService.dispatchRequest("myservice", "roundRobinStrategy");

        assertEquals(instance, selected);
        assertEquals(1, selected.getActiveConnections());
        verify(repository).save(selected);
        verify(metricsService).incrementRequestCount(1L);
    }
}
