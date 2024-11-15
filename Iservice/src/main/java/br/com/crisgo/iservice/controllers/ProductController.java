package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.RequestProductDTO;
import br.com.crisgo.iservice.DTO.response.ResponseProductDTO;
import br.com.crisgo.iservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResponseProductDTO> createProduct(@RequestBody @Validated RequestProductDTO requestProductDTO) {
        ResponseProductDTO createdProduct = productService.createProduct(requestProductDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("seller/{sellerId}/product/{name}")
    public ResponseEntity<ResponseProductDTO> getProductBySellerAndName(
            @PathVariable Long sellerId,
            @PathVariable String name) {
        ResponseProductDTO product = productService.findBySellerAndName(sellerId, name);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody @Validated RequestProductDTO productDetails) {
        ResponseProductDTO updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }
}
