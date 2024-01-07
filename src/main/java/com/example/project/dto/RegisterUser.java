package com.example.project.dto;

import com.example.project.entity.Adress;

public record RegisterUser(String firstName, String lastName, String email, String password, Adress adress) {
}
