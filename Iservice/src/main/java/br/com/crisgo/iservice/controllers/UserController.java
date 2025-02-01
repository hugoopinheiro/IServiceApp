package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.services.SellerService;
import br.com.crisgo.iservice.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;

@RestController
@RequestMapping(value = "/api/v1/user", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Tag(name = "usuario", description = "Controlador para salvar operações e editar dados do usuario")
public class UserController {
    private final UserService userService;
    private final SellerService sellerService;

    public UserController(UserService userService, SellerService sellerService) {
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<ResponseUserDTO>> getUser(@PathVariable Long id) {
        ResponseUserDTO userDTO = userService.findById(id);
        EntityModel<ResponseUserDTO> resource = EntityModel.of(userDTO);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EntityModel<ResponseUserDTO>> updateUser(@PathVariable Long id, @RequestBody @Validated RequestUserDTO requestUserDTODetails) {
        ResponseUserDTO updatedUser = userService.updateUser(id, requestUserDTODetails);
        EntityModel<ResponseUserDTO> resource = EntityModel.of(updatedUser);
        return ResponseEntity.ok(resource);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> createSeller(@PathVariable Long id, @RequestBody @Valid RequestSellerDTO requestSellerDTO) {
        ResponseUserDTO response = userService.createSeller(id, requestSellerDTO);
        return ResponseEntity.ok(response);
    }

}
