package com.heisyenberg.springchatserver.controllers;

import com.heisyenberg.springchatserver.models.User;
import com.heisyenberg.springchatserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signIn")
    User signIn(@RequestBody User user) {
        return userService.signIn(user.getUsername(), user.getPassword());
    }

    @PostMapping("/signUp")
    User signUp(@RequestBody User user) {
        return userService.signUp(user.getUsername(), user.getPassword());
    }
}
