package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.ResponseSellerDTO;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.services.AddressService;
import br.com.crisgo.iservice.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/seller")
@Validated
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    // Replace @RequestBody Seller with @RequestBody RequestSellerDTO in endpoints
    @PostMapping
    public ResponseEntity<ResponseSellerDTO> registerSeller(@RequestBody @Validated RequestSellerDTO requestSellerDTO) {
        ResponseSellerDTO responseSellerDTO = sellerService.saveOrUpdate(requestSellerDTO);
        return ResponseEntity.ok(responseSellerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseSellerDTO> updateSeller(@PathVariable Long id, @RequestBody @Validated RequestSellerDTO requestSellerDTO) {
        ResponseSellerDTO updatedSeller = sellerService.updateSeller(id, requestSellerDTO);
        return ResponseEntity.ok(updatedSeller);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSellerDTO> getSeller(@PathVariable Long id) {
        ResponseSellerDTO sellerDTO = sellerService.findById(id);
        return ResponseEntity.ok(sellerDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseSellerDTO>> getAllSellers() {
        List<ResponseSellerDTO> sellers = sellerService.findAll();
        return ResponseEntity.ok(sellers);
    }

}
