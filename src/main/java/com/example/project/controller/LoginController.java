package com.example.project.controller;

import com.example.project.converter.DtoConverter;
import com.example.project.dto.LoginUserRequest;
import com.example.project.dto.LoginUserResponse;
import com.example.project.entity.AppUser;
import com.example.project.entity.Role;
import com.example.project.exceptions.EcommerceException;
import com.example.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public LoginUserResponse login(@RequestBody LoginUserRequest loginUserRequest) {
        String username = loginUserRequest.email();
        String password = loginUserRequest.password();
        try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            AppUser user = userService.findByEmail(username);
            Role userRole = user.getauthorities().stream().findFirst().get();
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                return DtoConverter.convertToLoginUserResponse(user,userRole.getId().toString());
            }
        } catch (AuthenticationException e) {
            throw new EcommerceException("The user could not login", HttpStatus.UNAUTHORIZED);
        }
        throw new EcommerceException("The user could not login",HttpStatus.UNAUTHORIZED);
    }
}


