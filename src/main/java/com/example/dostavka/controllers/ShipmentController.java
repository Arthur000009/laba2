package com.example.dostavka.controllers;

import com.example.dostavka.models.Shipment;
import com.example.dostavka.services.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService ShipmentService;

    public ShipmentController(ShipmentService ShipmentService) {
        this.ShipmentService = ShipmentService;
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAll() {
        return ResponseEntity.ok(ShipmentService.getAllShipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getById(@PathVariable Long id) {
        Shipment Shipment = ShipmentService.getShipmentById(id);
        return Shipment != null ? ResponseEntity.ok(Shipment) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Shipment> create(@RequestBody Shipment Shipment, @RequestParam Long senderId) {
        try {
            Shipment created = ShipmentService.createShipment(Shipment, senderId);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Shipment> update(@PathVariable Long id, @RequestBody Shipment updates) {
        Shipment updated = ShipmentService.updateShipment(id, updates);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ShipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }
}