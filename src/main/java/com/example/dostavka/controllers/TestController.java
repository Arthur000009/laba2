package com.example.dostavka.controllers;

import com.example.dostavka.models.*;
import com.example.dostavka.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final UserService userService;
    private final RouteService routeService;
    private final ShipmentService shipmentService;
    private final StopService stopService;
    private final ShipmentAssignmentService assignmentService;

    public TestController(UserService userService, RouteService routeService,
                          ShipmentService shipmentService, StopService stopService,
                          ShipmentAssignmentService assignmentService) {
        this.userService = userService;
        this.routeService = routeService;
        this.shipmentService = shipmentService;
        this.stopService = stopService;
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<String> seedData() {
        User user = new User(null, "Иванов Иван", "ivantest@gmail.com");
        user = userService.createUser(user);

        Route route = new Route(null, "2012-12-12", 777.0, "PLANNED");
        route = routeService.createRoute(route);

        Shipment shipment = new Shipment(null, null, null, "ул. Авиамоторая, 8А", 5.5, "CREATED");
        shipment = shipmentService.createShipment(shipment, user.getId());

        Stop stop = new Stop(null, null, "ул. 2 Кабельный проезд, 4", 1);
        stop = stopService.createStop(stop, route.getId());

        ShipmentAssignment assignment = new ShipmentAssignment(null, null, null, "PENDING");
        assignmentService.createAssignment(assignment, shipment.getId(), stop.getId());

        return ResponseEntity.ok("Тестовые данные подготовлены");
    }
}