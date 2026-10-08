package com.example.dostavka.services;

import com.example.dostavka.models.Route;
import com.example.dostavka.models.ShipmentAssignment;
import com.example.dostavka.models.Stop;
import com.example.dostavka.repositories.RouteRepository;
import com.example.dostavka.repositories.StopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {

    private final StopRepository stopRepository;
    private final RouteRepository routeRepository;
    private final ShipmentAssignmentService assignmentService;

    public StopService(StopRepository stopRepository, RouteRepository routeRepository, ShipmentAssignmentService assignmentService) {
        this.stopRepository = stopRepository;
        this.routeRepository = routeRepository;
        this.assignmentService = assignmentService;
    }

    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    public Stop getStopById(Long id) {
        return stopRepository.findById(id);
    }

    public Stop createStop(Stop stop, Long routeId) {
        Route route = routeRepository.findById(routeId);
        if (route == null) {
            throw new IllegalArgumentException("Маршрут с ID " + routeId + " не найден");
        }
        stop.setRoute(route);
        return stopRepository.save(stop);
    }

    public Stop updateStop(Long id, Stop updates) {
        Stop existing = stopRepository.findById(id);
        if (existing != null) {
            if (updates.getAddress() != null) existing.setAddress(updates.getAddress());
            if (updates.getSequenceNumber() != null) existing.setSequenceNumber(updates.getSequenceNumber());
            return stopRepository.save(existing);
        }
        return null;
    }

    public void deleteStop(Long id) {
        List<ShipmentAssignment> assignments = assignmentService.getAllAssignments();
        for (ShipmentAssignment assignment : assignments) {
            if (assignment.getStop().getId().equals(id)) {
                assignmentService.deleteAssignment(assignment.getId());
            }
        }
        stopRepository.deleteById(id);
    }
}