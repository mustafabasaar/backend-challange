package com.example.project.controller;
import com.example.project.dto.RoleResponse;
import com.example.project.entity.Role;
import com.example.project.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    @PostMapping
    public RoleResponse save(@RequestBody Role role) {
        return roleService.save(role);
    }

}
