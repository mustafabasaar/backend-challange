package com.example.project.service;

import com.example.project.entity.AppUser;
import com.example.project.exceptions.EcommerceException;
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


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
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

}

