package com.example.project.controller;
import com.example.project.dto.RegisterUserResponse;
import com.example.project.dto.RoleConverter;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addrole/{id}")
    public RegisterUserResponse addrole(@PathVariable long id, @RequestBody RoleConverter roleConverter){
        userService.addRoleToUser(id,roleConverter);
        return new RegisterUserResponse("role added to person");
    }

    @PostMapping("/{id}")
    public String remove(@PathVariable Long id){
        userService.delete(id);
        return "User deleted";
    }
}


