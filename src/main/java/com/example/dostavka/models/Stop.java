package com.example.dostavka.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stop {
    private Long id;
    private Route route;
    private String address;
    private Integer sequenceNumber;
}