
package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.models.Address;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.models.User;
import br.com.crisgo.iservice.repositorys.UserRepository;

import br.com.crisgo.iservice.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Void> registerSeller(@RequestBody @Validated User user) {

        // Save the User first without an Address (address_id will be null)
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}