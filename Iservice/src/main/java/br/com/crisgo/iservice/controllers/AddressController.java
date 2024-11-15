package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.RequestAddressDTO;
import br.com.crisgo.iservice.DTO.response.ResponseAddressDTO;
import br.com.crisgo.iservice.DTO.response.ResponseSellerDTO;
import br.com.crisgo.iservice.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseAddressDTO>> getAllAddress() {
        List<ResponseAddressDTO> address = addressService.findAll();
        return ResponseEntity.ok(address);
    }


    @PostMapping
    public ResponseEntity<ResponseAddressDTO> registerAddress(@RequestBody @Validated RequestAddressDTO data) {
        ResponseAddressDTO createdAddress = addressService.createAddress(data);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }
}
