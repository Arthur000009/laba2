package com.example.dostavka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private Long id;
    private String date;
    private Double maxWeight;
    private String status;
}