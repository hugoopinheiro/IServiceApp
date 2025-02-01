package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseAddressDTO;
import br.com.crisgo.iservice.DTO.response.ResponseReviewsDTO;
import br.com.crisgo.iservice.DTO.response.ResponseSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.controllers.SellerController;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.models.*;
import br.com.crisgo.iservice.repositorys.BankAccountRepository;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import br.com.crisgo.iservice.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import br.com.crisgo.iservice.mapper.Mapper;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final Mapper modelMapper;
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    @Autowired
    public SellerService(SellerRepository sellerRepository, Mapper modelMapper, UserRepository userRepository, BankAccountRepository bankAccountRepository) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<ResponseSellerDTO> findAll() {
        List<Seller> sellers = sellerRepository.findAll();
        return modelMapper.map(sellers, ResponseSellerDTO.class);
    }

    public ResponseSellerDTO findById(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));
        ResponseSellerDTO responseSellerDTO = modelMapper.map(seller, ResponseSellerDTO.class);
        addHateoasLinks(responseSellerDTO);
        return responseSellerDTO;
    }
    @Transactional
    public ResponseSellerDTO createSeller(RequestSellerDTO requestSellerDTO, Long userId) {
        // Step 1: Create and save BankAccount
        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumberAccount(requestSellerDTO.getNumberAccount());
        bankAccount.setAgency(requestSellerDTO.getAgency());
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        if (savedBankAccount.getBankAccountId() == null) {
            throw new RuntimeException("Failed to save BankAccount");
        }

        // Step 2: Fetch the existing User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        // Step 3: Create the Seller and associate the BankAccount and User
        Seller seller = modelMapper.map(requestSellerDTO, Seller.class);
        seller.setBankAccount(savedBankAccount);
        seller.setUser(user); // No need to save the User again
        Seller savedSeller = sellerRepository.save(seller);

        // Step 4: Create the Response DTO
        ResponseSellerDTO responseSellerDTO = new ResponseSellerDTO();
        responseSellerDTO.setId(savedSeller.getId());
        responseSellerDTO.setName(user.getName());
        responseSellerDTO.setPhone(user.getContact());
        responseSellerDTO.setSellerDescription(savedSeller.getSellerDescription());
        responseSellerDTO.setCreatedAt(savedSeller.getCreatedAt());

        // Check if Address and User are already associated and mapped
        if (user.getAddress() != null) {
            ResponseAddressDTO responseAddressDTO = modelMapper.map(user.getAddress(), ResponseAddressDTO.class);
            responseSellerDTO.setResponseAddressDTO(responseAddressDTO);
        }

        ResponseUserDTO responseUserDTO = modelMapper.map(user, ResponseUserDTO.class);
        responseSellerDTO.setResponseUserDTO(responseUserDTO);

        return responseSellerDTO;
    }


    @Transactional
    public ResponseSellerDTO updateSeller(Long id, RequestSellerDTO requestSellerDTO) {
        Seller existingSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));

        // Map fields from requestSellerDTO onto existingSeller
        modelMapper.mapOntoExistingObject(requestSellerDTO, existingSeller);

        Seller updatedSeller = sellerRepository.save(existingSeller);
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
