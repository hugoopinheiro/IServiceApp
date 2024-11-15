package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RequestProductDTO;
import br.com.crisgo.iservice.DTO.response.ResponseProductDTO;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.mapper.DozerMapper;
import br.com.crisgo.iservice.models.Product;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.repositorys.ProductRepository;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import jakarta.transaction.Transactional;
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

    public ResponseProductDTO createProduct(RequestProductDTO requestProductDTO) {
        // Fetch the seller
        Seller seller = sellerRepository.findById(requestProductDTO.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));

        // Map RequestProductDTO to Product, then set the Seller
        Product product = DozerMapper.parseObject(requestProductDTO, Product.class);
        product.setSeller(seller);

        Product savedProduct = productRepository.save(product);

        return DozerMapper.parseObject(savedProduct, ResponseProductDTO.class);
    }

    public ResponseProductDTO findBySellerAndName(Long sellerId, String name) {
        // Retrieve Seller
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));

        // Find Product by Seller and Name
        Product product = productRepository.findBySellerAndName(seller, name)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with name: " + name));

        return DozerMapper.parseObject(product, ResponseProductDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto de ID " + id + " não encontrado");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public ResponseProductDTO updateProduct(Long id, RequestProductDTO productDetails) {
        // Fetch existing Product
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto de ID " + id + " não encontrado"));

        // Use Dozer to map the fields from productDetails onto the existing product
        DozerMapper.mapOntoExistingObject(productDetails, existingProduct);

        // Link the existing product to the correct seller if needed
        Seller seller = sellerRepository.findById(productDetails.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));
        existingProduct.setSeller(seller);

        // Save and return the updated product
        Product updatedProduct = productRepository.save(existingProduct);
        return DozerMapper.parseObject(updatedProduct, ResponseProductDTO.class);
    }
}
