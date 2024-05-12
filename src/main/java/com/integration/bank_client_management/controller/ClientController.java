package com.integration.bank_client_management.controller;

import com.integration.bank_client_management.constants.Constants;
import com.integration.bank_client_management.dto.ClientAddRequestDTO;
import com.integration.bank_client_management.dto.ClientDTO;
import com.integration.bank_client_management.dto.ClientDeleteResponseDTO;
import com.integration.bank_client_management.dto.ClientEditRequestDTO;
import com.integration.bank_client_management.errors.ExceptionDetail;
import com.integration.bank_client_management.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Operation(summary = "Gets client list")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Gets client list", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ClientDTO.class))), }),
            @ApiResponse(responseCode = "400", description = Constants.ERROR_BAD_REQUEST, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "500", description = Constants.ERROR_INTERNAL_SERVER, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) })
    })
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients(@Min(0) @RequestParam() int page, @Min(0) @RequestParam int size) throws Exception {
        return new ResponseEntity<>(clientService.getAllClients(page, size), HttpStatus.OK);
    }

    @Operation(summary = "Gets client by id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Gets client by id", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClientDTO.class)), }),
            @ApiResponse(responseCode = "400", description = Constants.ERROR_BAD_REQUEST, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "404", description = Constants.ERROR_NOT_FOUND, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "500", description = Constants.ERROR_INTERNAL_SERVER, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getUserByEmail(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
    }

    @Operation(summary = "Adds client")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Adds client", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClientDTO.class)), }),
            @ApiResponse(responseCode = "400", description = Constants.ERROR_BAD_REQUEST, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "409", description = Constants.ERROR_ALREADY_EXIST, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "500", description = Constants.ERROR_INTERNAL_SERVER, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) })
    })
    @PostMapping("/add")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientAddRequestDTO item) throws Exception {
        return new ResponseEntity<>(clientService.addClient(item), HttpStatus.OK);
    }

    @Operation(summary = "Edits client")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Edits client", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClientDTO.class)), }),
            @ApiResponse(responseCode = "400", description = Constants.ERROR_BAD_REQUEST, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "404", description = Constants.ERROR_NOT_FOUND, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "409", description = Constants.ERROR_ALREADY_EXIST, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "500", description = Constants.ERROR_INTERNAL_SERVER, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) })
    })
    @PutMapping("/edit")
    public ResponseEntity<ClientDTO> editClient(@Valid @RequestBody ClientEditRequestDTO item) throws Exception {
        return new ResponseEntity<>(clientService.editClient(item), HttpStatus.OK);
    }

    @Operation(summary = "Deletes client by id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletes client by id", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClientDTO.class)), }),
            @ApiResponse(responseCode = "404", description = Constants.ERROR_NOT_FOUND, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) }),
            @ApiResponse(responseCode = "500", description = Constants.ERROR_INTERNAL_SERVER, content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionDetail.class)) })
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ClientDeleteResponseDTO> deleteClient(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(clientService.deleteClient(id), HttpStatus.OK);
    }
}
