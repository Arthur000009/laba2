package com.example.dostavka.repositories;

import com.example.dostavka.models.Route;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RouteRepository {
    private final Map<Long, Route> database = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Route> findAll() {
        return new ArrayList<>(database.values());
    }

    public Route findById(Long id) {
        return database.get(id);
    }

    public Route save(Route route) {
        if (route.getId() == null) {
            route.setId(idGenerator.getAndIncrement());
        }
        database.put(route.getId(), route);
        return route;
    }

    public void deleteById(Long id) {
        database.remove(id);
    }
}