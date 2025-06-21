package br.com.crisgo.iservice.docs;

import br.com.crisgo.iservice.DTO.request.EmailRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;


public interface EmailControllerDocs {
    @Operation(summary = "Send an e-Mail",
            description = "Sends e-Mail by providing details subject and body",
            tags = {"Email"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse( description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<String> sendEmail(EmailRequestDTO emailRequestDTO);
    @Operation(summary = "Send an e-Mail with attachment",
            description = "Sends e-Mail with attachemnt by providing details subject and body",
            tags = {"Email"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse( description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    ResponseEntity<String> sendEmailWithAttachment(EmailRequestDTO emailRequestDTO);
}
