package com.example.project.service;

import com.example.project.dto.RoleConverter;
import com.example.project.dto.RoleResponse;
import com.example.project.entity.AppUser;
import com.example.project.entity.Role;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

   @Override
    public RoleResponse save(Role role) {
         roleRepository.save(role);
        return new RoleResponse("role created");
    }
}