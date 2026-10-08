package com.example.dostavka.services;

import com.example.dostavka.models.Shipment;
import com.example.dostavka.models.ShipmentAssignment;
import com.example.dostavka.models.Stop;
import com.example.dostavka.repositories.ShipmentAssignmentRepository;
import com.example.dostavka.repositories.ShipmentRepository;
import com.example.dostavka.repositories.StopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentAssignmentService {

    private final ShipmentAssignmentRepository assignmentRepository;
    private final ShipmentRepository shipmentRepository;
    private final StopRepository stopRepository;

    public ShipmentAssignmentService(
            ShipmentAssignmentRepository assignmentRepository,
            ShipmentRepository shipmentRepository,
            StopRepository stopRepository) {
        this.assignmentRepository = assignmentRepository;
        this.shipmentRepository = shipmentRepository;
        this.stopRepository = stopRepository;
    }

    public List<ShipmentAssignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public ShipmentAssignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    public ShipmentAssignment createAssignment(ShipmentAssignment assignment, Long shipmentId, Long stopId) {
        Shipment shipment = shipmentRepository.findById(shipmentId);
        if (shipment == null) {
            throw new IllegalArgumentException("Отправление с ID " + shipmentId + " не найдено");
        }

        Stop stop = stopRepository.findById(stopId);
        if (stop == null) {
            throw new IllegalArgumentException("Остановка с ID " + stopId + " не найдена");
        }

        assignment.setShipment(shipment);
        assignment.setStop(stop);
        return assignmentRepository.save(assignment);
    }

    public ShipmentAssignment updateAssignment(Long id, ShipmentAssignment updates) {
        ShipmentAssignment existing = assignmentRepository.findById(id);
        if (existing != null) {
            if (updates.getStatus() != null) existing.setStatus(updates.getStatus());
            return assignmentRepository.save(existing);
        }
        return null;
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}