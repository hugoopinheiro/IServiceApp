
package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.models.Address;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.models.User;
import br.com.crisgo.iservice.repositorys.UserRepository;

import br.com.crisgo.iservice.services.AddressService;
import br.com.crisgo.iservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id); // assuming userService is injected
    }

    @PostMapping
    public ResponseEntity<Void> registerUSer(@RequestBody @Validated User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
