package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RequestProductDTO;
import br.com.crisgo.iservice.DTO.response.ResponseProductDTO;
import br.com.crisgo.iservice.controllers.ProductController;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.mapper.Mapper;
import br.com.crisgo.iservice.models.Product;
import br.com.crisgo.iservice.models.Seller;
import br.com.crisgo.iservice.repositorys.ProductRepository;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final Mapper mapper;
    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.mapper = mapper;
    }

    public ResponseProductDTO createProduct(RequestProductDTO requestProductDTO) {
        // Fetch the seller
        Seller seller = sellerRepository.findById(requestProductDTO.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));

        // Map RequestProductDTO to Product, then set the Seller
        Product product = mapper.map(requestProductDTO, Product.class);
        product.setSeller(seller);

        Product savedProduct = productRepository.save(product);

        ResponseProductDTO responseProductDTO = mapper.map(savedProduct, ResponseProductDTO.class);
        addHateoasLinks(responseProductDTO);
        return responseProductDTO;
    }

    public ResponseProductDTO findBySellerAndName(Long sellerId, String name) {
        // Retrieve Seller
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));

        // Find Product by Seller and Name
        Product product = productRepository.findBySellerAndName(seller, name)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with name: " + name));

        ResponseProductDTO responseProductDTO = mapper.map(product, ResponseProductDTO.class);
        addHateoasLinks(responseProductDTO);
        return responseProductDTO;
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
        mapper.mapOntoExistingObject(productDetails, existingProduct);

        // Link the existing product to the correct seller if needed
        Seller seller = sellerRepository.findById(productDetails.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));
        existingProduct.setSeller(seller);

        // Save and return the updated product
        Product updatedProduct = productRepository.save(existingProduct);
        ResponseProductDTO responseProductDTO = mapper.map(updatedProduct, ResponseProductDTO.class);
        addHateoasLinks(responseProductDTO);
        return responseProductDTO;
    }

    private void addHateoasLinks(ResponseProductDTO productDTO) {
        //Link selfLink = linkTo(methodOn(ProductController.class).getProductBySellerAndName(productDTO.getId())).withSelfRel();
        Link updateLink = linkTo(methodOn(ProductController.class).updateProduct(productDTO.getProductId(), null)).withRel("update");
        Link deleteLink = linkTo(methodOn(ProductController.class).deleteProduct(productDTO.getProductId())).withRel("delete");

        //productDTO.add(selfLink);
        productDTO.add(updateLink);
        productDTO.add(deleteLink);
    }
}
