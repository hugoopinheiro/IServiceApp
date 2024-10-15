package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.services.AddressService;
import br.com.crisgo.iservice.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seller")
@Validated
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSeller() {
        List<Seller> sellers = sellerService.findAll();
        return ResponseEntity.ok(sellers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable Long id) {
        Seller seller = sellerService.findById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> registerSeller(@RequestBody Seller seller) {
        sellerService.saveOrUpdate(seller);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id){
        sellerService.deleteSellerById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody @Validated Seller sellerDetails) {
        Seller updatedSeller = sellerService.updateSeller(id, sellerDetails);
        return ResponseEntity.ok(updatedSeller);
    }

}
