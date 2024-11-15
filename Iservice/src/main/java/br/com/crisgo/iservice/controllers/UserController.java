package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/user", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<EntityModel<ResponseUserDTO>> getUser(@PathVariable Long id) {
        ResponseUserDTO userDTO = userService.findById(id);
        EntityModel<ResponseUserDTO> resource = EntityModel.of(userDTO);
        return ResponseEntity.ok(resource);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<EntityModel<ResponseUserDTO>> createUser(@RequestBody @Validated RequestUserDTO requestUserDTO) {
        ResponseUserDTO createdUser = userService.save(requestUserDTO);
        EntityModel<ResponseUserDTO> resource = EntityModel.of(createdUser);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<EntityModel<ResponseUserDTO>> updateUser(@PathVariable Long id, @RequestBody @Validated RequestUserDTO requestUserDTODetails) {
        ResponseUserDTO updatedUser = userService.updateUser(id, requestUserDTODetails);
        EntityModel<ResponseUserDTO> resource = EntityModel.of(updatedUser);
        return ResponseEntity.ok(resource);
    }
}
