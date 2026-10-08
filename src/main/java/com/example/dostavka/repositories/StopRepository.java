package com.example.dostavka.repositories;

import com.example.dostavka.models.Stop;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StopRepository {
    private final Map<Long, Stop> database = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Stop> findAll() {
        return new ArrayList<>(database.values());
    }

    public Stop findById(Long id) {
        return database.get(id);
    }

    public Stop save(Stop stop) {
        if (stop.getId() == null) {
            stop.setId(idGenerator.getAndIncrement());
        }
        database.put(stop.getId(), stop);
        return stop;
    }

    public void deleteById(Long id) {
        database.remove(id);
    }
}