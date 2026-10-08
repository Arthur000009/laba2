package com.example.dostavka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentAssignment {
    private Long id;
    private Shipment shipment;
    private Stop stop;
    private String status;
}