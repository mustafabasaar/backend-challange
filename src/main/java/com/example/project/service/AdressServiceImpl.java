

package com.example.project.service;
import com.example.project.converter.DtoConverter;
import com.example.project.dto.AdressResponse;
import com.example.project.entity.Adress;
import com.example.project.entity.AppUser;
import com.example.project.exceptions.EcommerceException;
import com.example.project.repository.AdressRepository;
import com.example.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.Optional;


@AllArgsConstructor
@Service
public class AdressServiceImpl implements AddressService {

    private final AdressRepository adressRepository;
    private UserRepository userRepository;



    @Override
    public Adress findById(Long id) {
        Optional<Adress> optionalAddress = adressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            return optionalAddress.get();
        }
        throw new EcommerceException("The address with given id does not exist. ID: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public AdressResponse save(Adress address,Long id) {
        Optional<AppUser> optionalUser = userRepository.findUserById(id);
        if(optionalUser.isPresent()){
            optionalUser.get().getAdresses().add(address);
            return DtoConverter.convertToAddressResponse(adressRepository.save(address));
        }
        throw new EcommerceException("The given id is wrong. So the address could not saved",HttpStatus.BAD_REQUEST);
    }



    @Override
    public AdressResponse delete(Long id) {
        Adress willDeleteAddress = findById(id);
        adressRepository.delete(willDeleteAddress);
        return DtoConverter.convertToAddressResponse(willDeleteAddress);
    }

    @Override
    public AdressResponse update(Long id, Adress address) {
        Adress willUpdateAddress = findById(id);
        willUpdateAddress.setAdress(address.getAdress());
        return DtoConverter.convertToAddressResponse(adressRepository.save(willUpdateAddress));
    }

    @Override
    public AdressResponse getById(Long id) {
        return DtoConverter.convertToAddressResponse(findById(id));
    }

}

