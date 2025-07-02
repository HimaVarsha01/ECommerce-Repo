package com.example.ECommerce.ECommercepro.Controller;

import com.example.ECommerce.ECommercepro.Dto.Userdto;
import com.example.ECommerce.ECommercepro.Model.Users;
import com.example.ECommerce.ECommercepro.Service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public Users register(@RequestBody @Valid Userdto dto) {
        return userService.registerUsers(dto);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }
}
