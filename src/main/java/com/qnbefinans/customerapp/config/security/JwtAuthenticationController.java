package com.qnbefinans.customerapp.config.security;


import com.qnbefinans.customerapp.customer.model.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Auth", description = "The Auth API. You can take your authorization token here easily after save.")
public class JwtAuthenticationController {

    private final JwtAuthenticationService jwtAuthenticationService;

    public JwtAuthenticationController(JwtAuthenticationService jwtAuthenticationService) {
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @Operation(summary = "Get token by customer if the customer is exist. Please make sure to create the customer!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the token",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Unhandled error",
                    content = @Content) })
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@Parameter(description = "customer information to be got token")
                                                           @RequestBody CustomerDto customerDto) {

        return ResponseEntity.ok(jwtAuthenticationService.createAuthenticationToken(customerDto));
    }
}
