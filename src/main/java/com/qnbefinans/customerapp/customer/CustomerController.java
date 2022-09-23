package com.qnbefinans.customerapp.customer;

import com.qnbefinans.customerapp.config.JwtResponse;
import com.qnbefinans.customerapp.customer.model.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){

        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String username){

        return ResponseEntity.ok().body(customerService.getCustomerDto(username));
    }

    @PostMapping("/save")
    public ResponseEntity<JwtResponse> saveCustomer(@RequestBody CustomerDto customerDto){

        return ResponseEntity.ok().body(customerService.save(customerDto));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto){

        return ResponseEntity.ok().body(customerService.update(customerDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable String username){

        return ResponseEntity.ok().body(customerService.delete(username));
    }
}
