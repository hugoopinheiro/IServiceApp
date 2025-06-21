package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.RequestBankAccountDTO;
import br.com.crisgo.iservice.DTO.request.RequestSellerDTO;
import br.com.crisgo.iservice.DTO.response.ResponseUserDTO;
import br.com.crisgo.iservice.DTO.request.RequestUserDTO;
import br.com.crisgo.iservice.services.SellerService;
import br.com.crisgo.iservice.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;

@RestController
@RequestMapping(value = "/api/v1/user")
@Tag(name = "usuario", description = "Controlador para salvar operações e editar dados do usuario")
public class UserController {
    private final UserService userService;
    private final SellerService sellerService;

    public UserController(UserService userService, SellerService sellerService) {
        this.userService = userService;
        this.sellerService = sellerService;
    }
    @Operation(summary = "get user by ID", description = "fetches a user by their unique ID")
    @ApiResponses(value ={
                @ApiResponse(responseCode = "200", description = "User found", content = @Content(schema = @Schema(implementation = ResponseUserDTO.class))),
                @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<ResponseUserDTO>> getUser(
            @Parameter(description = "User ID", required = true) @PathVariable Long id) {
        ResponseUserDTO userDTO = userService.findById(id);
        EntityModel<ResponseUserDTO> resource = EntityModel.of(userDTO);
        return ResponseEntity.ok(resource);
    }
    @Operation(summary = "Delete user by ID", description = "deletes an user by their unique ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "204", description = "user deleted successfully"),
            @ApiResponse(responseCode = "404", description = "user not found", content = @Content)
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID", required = true) @PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Update user details", description = "Updates an existing user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(schema = @Schema(implementation = ResponseUserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(
            @Parameter(description = "User ID", required = true) @PathVariable Long id, @RequestBody @Validated RequestUserDTO requestUserDTODetails) {

        ResponseUserDTO updatedUser = userService.updateUser(id, requestUserDTODetails);
        return ResponseEntity.ok(updatedUser);
    }

// Método para listar todos os usuários com paginação e filtros opcionais
// public ResponseEntity<Page<ResponseUserDTO>> listUsers(
//     @RequestParam(value = "page", defaultValue = "0") int page,
//     @RequestParam(value = "size", defaultValue = "10") int size,
//     @RequestParam(value = "name", required = false) String name,
//     @RequestParam(value = "email", required = false) String email);

// Método para realizar logout do usuário
// public ResponseEntity<Void> logoutUser(@RequestHeader("Authorization") String token);

// Método para alterar a senha do usuário
// public ResponseEntity<Void> changePassword(
//     @PathVariable Long id, 
//     @RequestBody @Validated ChangePasswordRequest passwordRequest);

// Método para iniciar processo de recuperação de senha
// public ResponseEntity<Void> resetPassword(@RequestBody @Validated ResetPasswordRequest resetPasswordRequest);

// Método para verificar a disponibilidade de e-mail ou nome de usuário
// public ResponseEntity<Boolean> checkAvailability(
//     @RequestParam(value = "email", required = false) String email,
//     @RequestParam(value = "username", required = false) String username);

// Método para atualizar o papel (role) do usuário
// public ResponseEntity<Void> updateRole(
//     @PathVariable Long id,
//     @RequestBody @Validated UpdateRoleRequest updateRoleRequest);

// Método para verificar e confirmar e-mail do usuário
// public ResponseEntity<Void> verifyEmail(@RequestParam(value = "token") String token);

// Método para reativar (habilitar) a conta do usuário
// public ResponseEntity<Void> enableUserAccount(@PathVariable Long id);
}
