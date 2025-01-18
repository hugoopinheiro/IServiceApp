package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.controllers.UserController;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.mapper.Mapper;
import br.com.crisgo.iservice.models.Address;
import br.com.crisgo.iservice.repositorys.AddressRepository;
import br.com.crisgo.iservice.repositorys.UserRepository;
import br.com.crisgo.iservice.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Mapper modelMapper;
    private final AddressRepository addressRepository;
    @Autowired
    public UserService(UserRepository userRepository, Mapper modelMapper, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
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
    public ResponseUserDTO save(RequestUserDTO requestUserDTO) {
        // Step 1: Create and save the Address
        Address address = new Address();
        address.setStreet(requestUserDTO.getStreet());
        address.setCep(requestUserDTO.getCep());
        address.setState(requestUserDTO.getState());
        address.setHouseNumber(requestUserDTO.getHouseNumber());

        Address savedAddress = addressRepository.save(address);

        // Step 2: Create the User and attach the Address
        User user = new User();
        user.setName(requestUserDTO.getName());
        user.setUserName(requestUserDTO.getUserName());
        user.setEmail(requestUserDTO.getEmail());
        user.setPassword(requestUserDTO.getPassword());
        user.setContact(requestUserDTO.getContact());
        user.setAddress(savedAddress); // Link the saved Address

        // Step 3: Save the User
        User savedUser = userRepository.save(user);

        // Step 4: Map and return the response
        return modelMapper.map(savedUser, ResponseUserDTO.class);
    }

    @Transactional
    public ResponseUserDTO updateUser(Long id, RequestUserDTO requestUserDTODetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));
        user.setName(requestUserDTODetails.getName());
        user.setEmail(requestUserDTODetails.getEmail());
        user.setContact(requestUserDTODetails.getContact());
        user.setPassword(requestUserDTODetails.getPassword());

        User updatedUser = userRepository.save(user);
        ResponseUserDTO responseUserDTO = modelMapper.map(updatedUser, ResponseUserDTO.class);
        addHateoasLinks(responseUserDTO);
        return responseUserDTO;
    }

    private void addHateoasLinks(ResponseUserDTO userDTO) {
        Link selfLink = linkTo(methodOn(UserController.class).getUser(userDTO.getUser_id())).withSelfRel();
        Link updateLink = linkTo(methodOn(UserController.class).updateUser(userDTO.getUser_id(), null)).withRel("update");
        Link deleteLink = linkTo(methodOn(UserController.class).deleteUser(userDTO.getUser_id())).withRel("delete");

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
