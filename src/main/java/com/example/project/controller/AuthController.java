package com.example.project.controller;
import com.example.project.dto.RegisterUser;
import com.example.project.dto.RegisterUserResponse;
import com.example.project.dto.RoleConverter;
import com.example.project.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegisterUserResponse register(@RequestBody RegisterUser registerUser){
         authenticationService
                .register(registerUser.getFirstName(),registerUser.getLastName(), registerUser.getEmail(), registerUser.getPassword());
         return new RegisterUserResponse("person registered");
    }



}
