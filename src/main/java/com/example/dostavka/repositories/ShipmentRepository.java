package com.example.dostavka.repositories;

import com.example.dostavka.models.Shipment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ShipmentRepository {
    private final Map<Long, Shipment> database = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Shipment> findAll() {
        return new ArrayList<>(database.values());
    }

    public Shipment findById(Long id) {
        return database.get(id);
    }

    public Shipment save(Shipment Shipment) {
        if (Shipment.getId() == null) {
            Shipment.setId(idGenerator.getAndIncrement());
        }
        database.put(Shipment.getId(), Shipment);
        return Shipment;
    }

    public void deleteById(Long id) {
        database.remove(id);
    }
}