package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.Product;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.repositorys.ProductRepository;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public Product createProduct(Product product, Long sellerId) {
        // Find the seller by ID
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));

        // Link the product to the seller
        product.setSeller(seller);

        // Save the product with the linked seller
        return productRepository.save(product);
    }

    public Product findBySellerAndName(Long sellerId, String name) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));

        return productRepository.findBySellerAndName(seller, name)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with name: " + name));
    }


    // Other product service methods...
}

