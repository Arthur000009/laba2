package com.example.dostavka.repositories;

import com.example.dostavka.models.ShipmentAssignment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ShipmentAssignmentRepository {
    private final Map<Long, ShipmentAssignment> database = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<ShipmentAssignment> findAll() {
        return new ArrayList<>(database.values());
    }

    public ShipmentAssignment findById(Long id) {
        return database.get(id);
    }

    public ShipmentAssignment save(ShipmentAssignment assignment) {
        if (assignment.getId() == null) {
            assignment.setId(idGenerator.getAndIncrement());
        }
        database.put(assignment.getId(), assignment);
        return assignment;
    }

    public void deleteById(Long id) {
        database.remove(id);
    }
}