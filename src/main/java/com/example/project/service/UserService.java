package com.example.project.service;

import com.example.project.dto.RoleConverter;
import com.example.project.dto.RoleResponse;
import com.example.project.entity.AppUser;
import com.example.project.entity.Role;
import com.example.project.exceptions.EcommerceException;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                    System.out.println("User credentials are not valid");
                    throw new UsernameNotFoundException("User credentials are not valid");
                });
    }

    public AppUser findByEmail(String email){
        Optional<AppUser> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new EcommerceException("The given email does not exist", HttpStatus.BAD_REQUEST);
    }

    public RoleResponse addRoleToUser(long id, RoleConverter roleConverter){
        Role userRole = roleRepository.findByAuthority(roleConverter.authority()).get();
        AppUser user = userRepository.findUserById(id).get();
        user.getauthorities().add(userRole);
        return new RoleResponse("Role add to user");
    }

    public Optional<AppUser> delete(Long id) {
      Optional<AppUser> user = userRepository.findById(id);
        if(user != null){
            userRepository.deleteById(id);
            return user;
        }
        throw new EcommerceException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}

