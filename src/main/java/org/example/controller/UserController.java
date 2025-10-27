package org.example.controller;

import org.example.dto.allUsers.AllUsersDto;
import org.example.dto.clientProducts.ClientProductsResponseDto;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    AllUsersDto findAllUsers() {
        return userService.findAllUsers();
    }
}
