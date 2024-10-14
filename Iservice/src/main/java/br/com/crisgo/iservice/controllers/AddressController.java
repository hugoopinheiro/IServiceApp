//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.RequestAddress;
import br.com.crisgo.iservice.models.Address;
import br.com.crisgo.iservice.repositorys.AddressRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/address"})
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    public AddressController() {
    }

    @GetMapping("/{id}")
    public ResponseEntity getAddress() {
        List<Address> allAddress = this.addressRepository.findAll();
        return ResponseEntity.ok(allAddress);
    }

    @PostMapping
    public ResponseEntity registerAddress(@RequestBody @Validated RequestAddress data) {
        Address address = new Address(data);
        System.out.println(data);
        System.out.println(address.getCep());
        this.addressRepository.save(address);
        return ResponseEntity.ok().build();
    }
}
