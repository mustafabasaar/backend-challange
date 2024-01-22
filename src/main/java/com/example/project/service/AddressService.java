

package com.example.project.service;

import com.example.project.dto.AdressResponse;

import com.example.project.entity.Adress;
import com.example.project.entity.Category;

import java.util.List;

public interface AddressService {
    Adress findById(Long id);
    AdressResponse save(Adress address,Long id);
    AdressResponse delete(Long id);
    AdressResponse update(Long id, Adress address);
    AdressResponse getById(Long id);
}

