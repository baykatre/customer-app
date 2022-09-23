package com.qnbefinans.customerapp.config;


import com.qnbefinans.customerapp.customer.model.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtAuthenticationController {

    private final JwtAuthenticationService jwtAuthenticationService;

    public JwtAuthenticationController(JwtAuthenticationService jwtAuthenticationService) {
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody CustomerDto customerDto) {

        return ResponseEntity.ok(jwtAuthenticationService.createAuthenticationToken(customerDto));
    }
}
