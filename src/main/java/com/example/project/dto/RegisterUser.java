package com.example.project.dto;

import com.example.project.entity.Adress;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUser {
   private String firstName ;
  private  String lastName;
   private String email;
  private  String password;
}
