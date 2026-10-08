package com.example.dostavka.services;

import com.example.dostavka.models.Shipment;
import com.example.dostavka.models.User;
import com.example.dostavka.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ShipmentService shipmentService;

    public UserService(UserRepository userRepository, ShipmentService shipmentService) {
        this.userRepository = userRepository;
        this.shipmentService = shipmentService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userUpdates) {
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            if (userUpdates.getName() != null) {
                existingUser.setName(userUpdates.getName());
            }
            if (userUpdates.getEmail() != null) {
                existingUser.setEmail(userUpdates.getEmail());
            }
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        List<Shipment> shipments = shipmentService.getAllShipments();
        for (Shipment shipment : shipments) {
            if (shipment.getSender().getId().equals(id)) {
                shipmentService.deleteShipment(shipment.getId());
            }
        }
        userRepository.deleteById(id);
    }
}