package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.controllers.UserController;
import br.com.crisgo.iservice.exceptions.EntityNotFoundException;
import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.mapper.Mapper;
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
    @Autowired
    public UserService(UserRepository userRepository, Mapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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

    public ResponseUserDTO save(RequestUserDTO requestUserDTO) {
        User user = modelMapper.map(requestUserDTO, User.class);;
        User savedUser = userRepository.save(user);
        ResponseUserDTO responseUserDTO = modelMapper.map(savedUser, ResponseUserDTO.class);
        addHateoasLinks(responseUserDTO);
        return responseUserDTO;
    }

    @Transactional
    public ResponseUserDTO updateUser(Long id, RequestUserDTO requestUserDTODetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario de ID " + id + " não encontrado"));
        user.setName(requestUserDTODetails.getName());
        user.setEmail(requestUserDTODetails.getEmail());
        user.setPhone(requestUserDTODetails.getPhone());
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
