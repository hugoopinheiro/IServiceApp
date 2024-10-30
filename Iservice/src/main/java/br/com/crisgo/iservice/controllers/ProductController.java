package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.models.Product;
import br.com.crisgo.iservice.models.User;
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
    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<Product> createProduct(@PathVariable Long sellerId, @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product, sellerId);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    @GetMapping("seller/{sellerId}/product/{name}")
    public ResponseEntity<Product> getProductBySellerAndName(
            @PathVariable Long sellerId,
            @PathVariable String name) {

        // Find the product by seller and name
        Product product = productService.findBySellerAndName(sellerId, name);

        return ResponseEntity.ok(product);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateUser(@PathVariable Long id, @RequestBody @Validated Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }
}