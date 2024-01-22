package com.example.project.controller;


import com.example.project.dto.AdressResponse;
import com.example.project.entity.Adress;
import com.example.project.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AdressResponse save(@Validated @RequestBody Adress address,  @PathVariable Long id) {
        return addressService.save(address, id);
    }


}
