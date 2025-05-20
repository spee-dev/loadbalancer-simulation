package com.ankit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_instance")
public class ServiceInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private String ipAddress;
    private int port;
    private boolean healthy = true;
    private int weight = 1;
    private int activeConnections = 0;
    private boolean decommissioned = false;
    private String metadata;
}
