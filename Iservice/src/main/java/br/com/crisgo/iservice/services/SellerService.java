package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.controllers.AuthenticationController;
import br.com.crisgo.iservice.controllers.SellerController;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.*;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import br.com.crisgo.iservice.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import br.com.crisgo.iservice.mapper.Mapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final Mapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository, Mapper modelMapper, UserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<ResponseSellerDTO> findAll(){
        List<Seller> sellers = sellerRepository.findAll();
        return sellers.stream()
                .map(seller -> modelMapper.map(seller, ResponseSellerDTO.class))
                .toList();
    }

    public ResponseSellerDTO findById(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário nao encontrado"));
        ResponseSellerDTO responseSellerDTO = modelMapper.map(seller, ResponseSellerDTO.class);
        addHateoasLinks(responseSellerDTO);
        return responseSellerDTO;
    }
    @Transactional
    public ResponseUserDTO createSeller(Long userId, RequestSellerDTO requestSellerDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (user.getRole().equals(Role.SELLER)) {
            throw new IllegalStateException("Usuário já é um vendedor.");
        }

        BankAccount bankAccount = BankAccount.builder()
                .agency(requestSellerDTO.getAgency())
                .numberAccount(requestSellerDTO.getNumberAccount())
                .build();

        Seller seller = Seller.builder()
                .user(user)
                .cpf(requestSellerDTO.getCpf())
                .sellerDescription(requestSellerDTO.getSellerDescription())
                .bankAccount(bankAccount)
                .createdAt(LocalDateTime.now())
                .build();

        sellerRepository.save(seller);

        user.setRole(Role.SELLER);
        userRepository.save(user);

        return ResponseUserDTO.builder()
                .id(user.getUserId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
    }

    @Transactional
    public ResponseSellerDTO updateSeller(Long id, RequestSellerDTO requestSellerDTO) {
        Seller existingSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));

        // Map fields from requestSellerDTO onto existingSeller
        Seller updatedSeller = existingSeller.toBuilder()
                .cpf(requestSellerDTO.getCpf() != null ? requestSellerDTO.getCpf() : existingSeller.getCpf() )
                .sellerDescription(requestSellerDTO.getSellerDescription() != null ? requestSellerDTO.getSellerDescription() : existingSeller.getSellerDescription())
                .bankAccount(existingSeller.getBankAccount())
                .createdAt(existingSeller.getCreatedAt())
                .build();

        sellerRepository.save(updatedSeller);

        ResponseSellerDTO responseSellerDTO = modelMapper.map(updatedSeller, ResponseSellerDTO.class);
        addHateoasLinks(responseSellerDTO);
        return responseSellerDTO;
    }

    @Transactional
    public void deleteById(Long id) {
        if (!sellerRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario de ID " + id + " não encontrado");
        }
        sellerRepository.deleteById(id);
    }

    private void addHateoasLinks(ResponseSellerDTO sellerDTO) {
        Link selfLink = linkTo(methodOn(SellerController.class).getSeller(sellerDTO.getId())).withSelfRel();
        Link updateLink = linkTo(methodOn(SellerController.class).updateSeller(sellerDTO.getId(), null)).withRel("update");
        Link deleteLink = linkTo(methodOn(SellerController.class).deleteSeller(sellerDTO.getId())).withRel("delete");

        sellerDTO.add(selfLink);
        sellerDTO.add(updateLink);
        sellerDTO.add(deleteLink);
    }
}