package com.example.ECommerce.ECommercepro.Service;


import com.example.ECommerce.ECommercepro.Model.Users;
import com.example.ECommerce.ECommercepro.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class UsersService {

    @Autowired
    private UsersRepo usersRepository;

    public Users registerUsers(Users dto) {
        if (usersRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Users user = new Users();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // Password should be encrypted later
        user.setRoles(Set.of("ROLE_USER"));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        Users savedUser = usersRepository.save(user);

        // Kafka event
        String event = String.format(
                "{\"event\":\"USER_REGISTERED\",\"email\":\"%s\",\"name\":\"%s\",\"timestamp\":\"%s\"}",
                savedUser.getEmail(), savedUser.getName(), savedUser.getCreatedAt()
        );
        KafkaProducerService.send("user-registered", event);

        return savedUser;

    }

    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }


    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}

