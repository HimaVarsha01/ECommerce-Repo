package com.example.ECommerce.ECommercepro.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "Welcome, User!";
    }
}

