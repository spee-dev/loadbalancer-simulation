package com.ankit.config;

import com.ankit.stratgey.LeastConnectionsStrategy;
import com.ankit.stratgey.LoadBalancingStrategy;
import com.ankit.stratgey.RoundRobinStrategy;
import com.ankit.stratgey.WeightedStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class LoadBalancerConfig {
    @Bean
    public Map<String, LoadBalancingStrategy> strategies(
            RoundRobinStrategy rr,
            LeastConnectionsStrategy lc,
            WeightedStrategy weighted) {
        Map<String, LoadBalancingStrategy> map = new HashMap<>();
        map.put("roundRobinStrategy", rr);
        map.put("leastConnectionsStrategy", lc);
        map.put("weightedStrategy", weighted);
        return map;
    }
}
