package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/seller")
@Validated
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ResponseUserDTO> createSeller(@RequestBody @Validated RequestSellerDTO requestSellerDTO,
                                                          @PathVariable Long userId) {
        ResponseUserDTO responseUserDTO = sellerService.createSeller(userId, requestSellerDTO);
        return ResponseEntity.ok(responseUserDTO);
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
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
