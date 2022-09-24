package com.qnbefinans.customerapp.customer;

import com.qnbefinans.customerapp.config.security.JwtAuthenticationService;
import com.qnbefinans.customerapp.config.security.JwtResponse;
import com.qnbefinans.customerapp.customer.model.Customer;
import com.qnbefinans.customerapp.customer.model.CustomerDto;
import com.qnbefinans.customerapp.customer.model.CustomerMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationService jwtAuthenticationService;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtAuthenticationService jwtAuthenticationService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    public List<CustomerDto> getAllCustomers() {
        return CustomerMapper.INSTANCE.convertToCustomerDtoList(customerRepository.findAll());
    }

    public CustomerDto getCustomerDto(String username) {
        final Customer customer = customerRepository
                .findById(username)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
        return CustomerMapper.INSTANCE.convertToCustomerDto(customer);
    }

    public JwtResponse save(CustomerDto customerDto) {

        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(customerDto);
        encryptPass(customerDto, customer);

        customerRepository.save(customer);
        return jwtAuthenticationService.createAuthenticationToken(customerDto);
    }

    public CustomerDto update(CustomerDto customerDto) {

        final Customer customer = customerRepository
                .findById(customerDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        encryptPass(customerDto, customer);

        final Customer updatedCustomer = customerRepository.save(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDto(updatedCustomer);
    }

    public String delete(String username) {

        if(!customerRepository.existsById(username)){
            return "Customer not found!";
        }

        customerRepository.deleteById(username);
        return "User deleted successfully";
    }

    private void encryptPass(CustomerDto customerDto, Customer customer) {
        final String encryptedPassword = passwordEncoder.encode(customerDto.getPassword());
        customer.setPassword(encryptedPassword);
    }
}
