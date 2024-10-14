
package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.models.Address;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.repositorys.SellerRepository;

import br.com.crisgo.iservice.services.AddressService;
import br.com.crisgo.iservice.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;
//    @GetMapping()
//    public ResponseEntity<Seller> getAllSeller() {
//        sellerService.findAll(); // assuming sellerService is injected
//        return null;
//    }
    @GetMapping("/{id}")
    public Seller getSeller(@PathVariable Long id) {
        return sellerService.findById(id); // assuming sellerService is injected
    }
    @Autowired
    private AddressService addressService;
    @Autowired
    SellerService sellerService;


    @PostMapping
    public ResponseEntity<Void> registerSeller(@RequestBody @Validated Seller seller) {
        // The address will automatically be saved when saving the seller, no need to save it separately
        sellerRepository.save(seller);
        return ResponseEntity.ok().build();
    }

}
