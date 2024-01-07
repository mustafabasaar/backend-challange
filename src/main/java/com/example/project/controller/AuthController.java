package com.example.project.controller;


import com.example.project.dto.RegisterUser;
import com.example.project.entity.AppUser;
import com.example.project.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterUser registerUser){
        return authenticationService
                .register(registerUser.firstName(),registerUser.lastName(), registerUser.email(), registerUser.password(),registerUser.adress());
    }
}
