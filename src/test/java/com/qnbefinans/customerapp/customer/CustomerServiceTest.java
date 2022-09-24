package com.qnbefinans.customerapp.customer;

import com.qnbefinans.customerapp.config.security.JwtAuthenticationService;
import com.qnbefinans.customerapp.config.security.JwtResponse;
import com.qnbefinans.customerapp.customer.model.Customer;
import com.qnbefinans.customerapp.customer.model.CustomerDto;
import com.qnbefinans.customerapp.customer.model.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtAuthenticationService jwtAuthenticationService;

    @BeforeEach
    public void init() {
        customerService = new CustomerService(customerRepository, passwordEncoder, jwtAuthenticationService);
    }

    @Test
    public void getCustomer_idIsValid_successReturn() {

        //Arrange
        final String username = "anil";
        final Customer customer = new Customer();
        customer.setUsername(username);

        when(customerRepository.findById(username)).thenReturn(Optional.of(customer));

        //Act
        final CustomerDto customerDto = customerService.getCustomerDto(username);

        //Assert
        assertEquals("anil", customerDto.getUsername());
    }

    @Test
    public void getCustomer_idIsInvalid_throwRuntimeException() {

        //Arrange
        final String username = "anil";

        when(customerRepository.findById(username)).thenReturn(Optional.empty());

        //Act
        //Assert
        assertThrows(RuntimeException.class, () -> {
            customerService.getCustomerDto(username);
        });
    }

    @Test
    public void saveCustomer_validRequest_successReturn() {

        //Arrange
        final Customer customer = new Customer();
        customer.setUsername("anil");
        final JwtResponse jwtResponse = new JwtResponse("anil", "1234");

        final CustomerDto customerDto = CustomerMapper.INSTANCE.convertToCustomerDto(customer);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(jwtAuthenticationService.createAuthenticationToken(customerDto)).thenReturn(jwtResponse);

        //Act
        final JwtResponse savedJwtResponse = customerService.save(customerDto);

        //Assert
        assertInstanceOf(JwtResponse.class, savedJwtResponse);
        assertEquals("anil", savedJwtResponse.getUsername());
        assertEquals("1234", savedJwtResponse.getToken());
    }
}
