package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.config.SecurityConfig;
import br.com.crisgo.iservice.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;

@RestController
@RequestMapping(value = "/user", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Tag(name = "usuario", description = "Controlador para salvar operações e editar dados do usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Operation(summary = "Busca dados de um usuario especifico", description = "Metodo para busca dados de um usuario")
    @ApiResponse(responseCode = "200", description = "usuario encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "usuario nao existe")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
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
