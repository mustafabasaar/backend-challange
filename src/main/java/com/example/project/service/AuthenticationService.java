package com.example.project.service;

import com.example.project.dto.RoleConverter;
import com.example.project.dto.RoleResponse;
import com.example.project.entity.Adress;
import com.example.project.entity.AppUser;
import com.example.project.entity.Role;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser register(String firstName, String lastName, String email, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("ADMIN").get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        AppUser user = new AppUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(roles);
        return userRepository.save(user);
    }


}
