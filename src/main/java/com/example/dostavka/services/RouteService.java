package com.example.dostavka.services;

import com.example.dostavka.models.Route;
import com.example.dostavka.models.Stop;
import com.example.dostavka.repositories.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final StopService stopService;

    public RouteService(RouteRepository routeRepository, StopService stopService) {
        this.routeRepository = routeRepository;
        this.stopService = stopService;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, Route updates) {
        Route existing = routeRepository.findById(id);
        if (existing != null) {
            if (updates.getDate() != null) existing.setDate(updates.getDate());
            if (updates.getMaxWeight() != null) existing.setMaxWeight(updates.getMaxWeight());
            if (updates.getStatus() != null) existing.setStatus(updates.getStatus());
            return routeRepository.save(existing);
        }
        return null;
    }

    public void deleteRoute(Long id) {
        List<Stop> stops = stopService.getAllStops();
        for (Stop stop : stops) {
            if (stop.getRoute().getId().equals(id)) {
                stopService.deleteStop(stop.getId());
            }
        }
        routeRepository.deleteById(id);
    }
}