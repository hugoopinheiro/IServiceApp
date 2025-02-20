package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.RegisterDTO;
import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.controllers.AuthenticationController;
import br.com.crisgo.iservice.controllers.UserController;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.infra.TokenService;
import br.com.crisgo.iservice.mapper.Mapper;
import br.com.crisgo.iservice.models.*;
import br.com.crisgo.iservice.repositorys.AddressRepository;
import br.com.crisgo.iservice.repositorys.SellerRepository;
import br.com.crisgo.iservice.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final UserRepository userRepository;
    private final Mapper modelMapper;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final SellerRepository sellerRepository;
    @Autowired
    public UserService(
            UserRepository userRepository,
            Mapper modelMapper,
            AddressRepository addressRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService,
            SellerRepository sellerRepository)
    {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.sellerRepository = sellerRepository;
    }

    public ResponseUserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));
        ResponseUserDTO responseUserDTO = modelMapper.map(user, ResponseUserDTO.class);
        addHateoasLinks(responseUserDTO);
        return responseUserDTO;
    }

    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario de ID " + id + " não encontrado");
        }
        userRepository.deleteById(id);
    }
    @Transactional
    public RegisterDTO register(RegisterDTO registerDTO) {
        // Step 1: Create and save the Address
        Address address = new Address();
        address.setStreet(registerDTO.street());
        address.setCep(registerDTO.cep());
        address.setComplement(registerDTO.complement());
        address.setState(registerDTO.state());
        address.setHouseNumber(registerDTO.houseNumber());

        Address savedAddress = addressRepository.save(address);

        // Convert Register DTO to User Entity using ModelMapper
        User user = User.builder()
                .name(registerDTO.name())
                .userName(registerDTO.username())
                .login(registerDTO.login())
                .contact(registerDTO.contact())
                .password(passwordEncoder.encode(registerDTO.password()))
                .address(savedAddress)
                .role(Role.COMMON_USER)  // Default role
                .build();


        userRepository.save(user);

        logger.info("User '{}' registered successfully", registerDTO.login());
        return registerDTO;
    }

    @Transactional
    public ResponseUserDTO updateUser(Long id, RequestUserDTO requestUserDTODetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));
        user.setName(requestUserDTODetails.getName());
        user.setLogin(requestUserDTODetails.getLogin());
        user.setContact(requestUserDTODetails.getContact());
        user.setPassword(requestUserDTODetails.getPassword());

        User updatedUser = userRepository.save(user);

        ResponseUserDTO responseUserDTO = ResponseUserDTO.builder()
                .id(updatedUser.getUserId())
                .name(updatedUser.getName())
                .username(updatedUser.getUsername())
                .login(updatedUser.getLogin())
                .contact(updatedUser.getContact())
                .build();

        addHateoasLinks(responseUserDTO);
        return responseUserDTO;
    }

//    @Transactional
//    public ResponseUserDTO createSeller(Long userId, RequestSellerDTO requestSellerDTO) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
//
//        if (user.getRole().equals(Role.SELLER)) {
//            throw new IllegalStateException("Usuário já é um vendedor.");
//        }
//
//        logger.info("User: {}", user);
//
//        // Create BankAccount
//        BankAccount bankAccount = BankAccount.builder()
//                .agency(requestSellerDTO.getAgency())
//                .numberAccount(requestSellerDTO.getNumberAccount())
//                .build();
//
//
//        // ✅ Map Seller DTO
//        Seller seller = modelMapper.map(requestSellerDTO, Seller.class);
//        seller.setUser(user);
//        seller.setBankAccount(bankAccount);
//        sellerRepository.save(seller);
//
//        // ✅ Upgrade user role
//        user.setRole(Role.SELLER);
//        userRepository.save(user);
//
//        return modelMapper.map(seller, ResponseSellerDTO.class).getResponseUserDTO();
//    }


    private void addHateoasLinks(ResponseUserDTO userDTO) {
        Link selfLink = linkTo(methodOn(UserController.class).getUser(userDTO.getId())).withSelfRel();
        Link updateLink = linkTo(methodOn(UserController.class).updateUser(userDTO.getId(), null)).withRel("update");
        Link deleteLink = linkTo(methodOn(UserController.class).deleteUser(userDTO.getId())).withRel("delete");

        userDTO.add(selfLink);
        userDTO.add(updateLink);
        userDTO.add(deleteLink);
    }




//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        var user = userRepository.findByUsername(username);
//        if (user != null){
//            return user;
//        } else {
//            throw new UsernameNotFoundException("Username " + username + " not found");
//        }
//    }
}
