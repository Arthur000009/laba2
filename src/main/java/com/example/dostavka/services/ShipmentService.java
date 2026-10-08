package com.example.dostavka.services;

import com.example.dostavka.models.Shipment;
import com.example.dostavka.models.ShipmentAssignment;
import com.example.dostavka.models.User;
import com.example.dostavka.repositories.ShipmentRepository;
import com.example.dostavka.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final UserRepository userRepository;
    private final ShipmentAssignmentService assignmentService;

    public ShipmentService(ShipmentRepository shipmentRepository, UserRepository userRepository, ShipmentAssignmentService assignmentService) {
        this.shipmentRepository = shipmentRepository;
        this.userRepository = userRepository;
        this.assignmentService = assignmentService;
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }

    public Shipment createShipment(Shipment Shipment, Long senderId) {
        User sender = userRepository.findById(senderId);
        if (sender == null) {
            throw new IllegalArgumentException("Пользователь с ID " + senderId + " не найден");
        }
        Shipment.setSender(sender);
        Shipment.setCreatedAt(java.time.LocalDateTime.now());
        return shipmentRepository.save(Shipment);
    }

    public Shipment updateShipment(Long id, Shipment updates) {
        Shipment existing = shipmentRepository.findById(id);
        if (existing != null) {
            if (updates.getAddress() != null) existing.setAddress(updates.getAddress());
            if (updates.getWeight() != null) existing.setWeight(updates.getWeight());
            if (updates.getStatus() != null) existing.setStatus(updates.getStatus());
            return shipmentRepository.save(existing);
        }
        return null;
    }

    public void deleteShipment(Long id) {
        List<ShipmentAssignment> assignments = assignmentService.getAllAssignments();
        for (ShipmentAssignment assignment : assignments) {
            if (assignment.getShipment().getId().equals(id)) {
                assignmentService.deleteAssignment(assignment.getId());
            }
        }
        shipmentRepository.deleteById(id);
    }
}