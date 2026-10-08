package com.example.dostavka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    private java.time.LocalDateTime createdAt;
    private Long id;
    private User sender;
    private String address;
    private Double weight;
    private String status;
}