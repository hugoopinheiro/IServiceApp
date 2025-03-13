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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseProductDTO createProduct(RequestProductDTO requestProductDTO, Long sellerId) {
        // Fetch the seller
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor não encontrado"));

        // Map RequestProductDTO to Product, then set the Seller
        Product product = Product.builder()
                .createdAt(LocalDateTime.now())
                .price(requestProductDTO.getPrice())
                .category(requestProductDTO.getCategory())
                .description(requestProductDTO.getDescription())
                .name(requestProductDTO.getName())
                .seller(seller)
                .build();

        Product savedProduct = productRepository.save(product);

        ResponseProductDTO responseProductDTO = mapper.map(savedProduct, ResponseProductDTO.class);
        addHateoasLinks(responseProductDTO);
        return responseProductDTO;
    }

    public List<ResponseProductDTO> findBySeller(Long sellerId) {
        // Retrieve Seller
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));

        // Find Products by Seller
        List<Product> products = productRepository.findBySeller(seller);

        // Check if there are no products
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found for seller: " + seller.getUser().getName());
        }

        // Convert List<Product> to List<ResponseProductDTO>
        List<ResponseProductDTO> responseProductDTOs = products.stream()
                .map(product -> mapper.map(product, ResponseProductDTO.class))
                .collect(Collectors.toList());

        // Add HATEOAS links to each DTO
        responseProductDTOs.forEach(this::addHateoasLinks);

        return responseProductDTOs;
    }


    @Transactional
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto de ID " + id + " não encontrado");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public ResponseProductDTO updateProduct(Long id, RequestProductDTO requestProductDTO) {
        // Fetch existing Product
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto de ID " + id + " não encontrado"));

        // Fetch seller entity from the database
        Seller seller = sellerRepository.findById(requestProductDTO.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Vendedor de ID " + requestProductDTO.getSellerId() + " não encontrado"));

        // Update existing product fields instead of creating a new object
        existingProduct.setPrice(requestProductDTO.getPrice());
        existingProduct.setCategory(requestProductDTO.getCategory());
        existingProduct.setDescription(requestProductDTO.getDescription());
        existingProduct.setName(requestProductDTO.getName());
        existingProduct.setSeller(seller); // Set the fetched Seller

        // Save and return the updated product
        productRepository.save(existingProduct);
        ResponseProductDTO responseProductDTO = mapper.map(existingProduct, ResponseProductDTO.class);
        addHateoasLinks(responseProductDTO);
        return responseProductDTO;
    }



    private void addHateoasLinks(ResponseProductDTO productDTO) {
        //Link selfLink = linkTo(methodOn(ProductController.class).getProductBySellerAndName(productDTO.getId())).withSelfRel();
//        Link updateLink = linkTo(methodOn(ProductController.class).updateProduct(productDTO.getProductId(), null)).withRel("update");
        Link deleteLink = linkTo(methodOn(ProductController.class).deleteProduct(productDTO.getProductId())).withRel("delete");

        //productDTO.add(selfLink);
//        productDTO.add(updateLink);
        productDTO.add(deleteLink);
    }
}
