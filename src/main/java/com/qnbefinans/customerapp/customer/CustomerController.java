package com.qnbefinans.customerapp.customer;

import com.qnbefinans.customerapp.config.security.JwtResponse;
import com.qnbefinans.customerapp.customer.model.Customer;
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

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name = "Customer", description = "The Customer API. Contains all the operations that can be performed on a customer.")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all of the customers successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){

        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @Operation(summary = "Get a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content) })
    @GetMapping("/{username}")
    public ResponseEntity<CustomerDto> getCustomer(@Parameter(description = "id of customer to be searched")
                                                       @PathVariable String username){

        return ResponseEntity.ok().body(customerService.getCustomerDto(username));
    }

    @Operation(summary = "Save a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved the customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Unhandled error",
                    content = @Content) })
    @PostMapping("/save")
    public ResponseEntity<JwtResponse> saveCustomer(@Parameter(description = "customer information to be saved")
                                                        @RequestBody CustomerDto customerDto){

        return ResponseEntity.ok().body(customerService.save(customerDto));
    }

    @Operation(summary = "Update a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Unhandled error",
                    content = @Content) })
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@Parameter(description = "customer information to be updated")
                                                          @RequestBody CustomerDto customerDto){

        return ResponseEntity.ok().body(customerService.update(customerDto));
    }

    @Operation(summary = "Delete a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the customer",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", description = "Unhandled error",
                    content = @Content) })
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@Parameter(description = "customer username to be deleted")
                                                     @PathVariable String username){

        return ResponseEntity.ok().body(customerService.delete(username));
    }
}
