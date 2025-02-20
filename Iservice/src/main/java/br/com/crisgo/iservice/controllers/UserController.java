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
    public ResponseEntity<EntityModel<ResponseUserDTO>> updateUser(
            @Parameter(description = "User ID", required = true) @PathVariable Long id, @RequestBody @Validated RequestUserDTO requestUserDTODetails) {
        ResponseUserDTO updatedUser = userService.updateUser(id, requestUserDTODetails);
        EntityModel<ResponseUserDTO> resource = EntityModel.of(updatedUser);
        return ResponseEntity.ok(resource);
    }
//    @Operation(summary = "Create seller", description = "Converts a user into a seller")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User converted to seller successfully", content = @Content(schema = @Schema(implementation = ResponseUserDTO.class))),
//            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
//            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
//    })
//    @PostMapping("/{id}")
//    public ResponseEntity<ResponseUserDTO> createSeller(
//            @PathVariable Long id,
//            @RequestBody @Valid RequestSellerDTO requestSellerDTO) { // ✅ Only one @RequestBody
//
//        ResponseUserDTO response = userService.createSeller(id, requestSellerDTO);
//        return ResponseEntity.ok(response);
//    }




}
