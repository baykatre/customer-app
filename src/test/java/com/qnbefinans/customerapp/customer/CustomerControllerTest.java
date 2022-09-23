package com.qnbefinans.customerapp.customer;

import com.qnbefinans.customerapp.config.JwtResponse;
import com.qnbefinans.customerapp.customer.model.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void init() {
        customerController = new CustomerController(customerService);
    }

    @Test
    public void getAllCustomers_getSuccess() {

        //Arrange
        when(customerService.getAllCustomers()).thenReturn(List.of(new CustomerDto()));

        //Act
        final ResponseEntity<List<CustomerDto>> responseEntity = customerController.getAllCustomers();
        final List<CustomerDto> responseEntityBody = responseEntity.getBody();

        assertInstanceOf(List.class, responseEntityBody);
        assertInstanceOf(CustomerDto.class, responseEntityBody.get(0));
    }

    @Test
    public void getCustomer_idIsValid_successReturn() {

        //Arrange
        final String username = "anil";
        final CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername(username);

        when(customerService.getCustomerDto(username)).thenReturn(customerDto);

        //Act
        final ResponseEntity<CustomerDto> responseEntity = customerController.getCustomer(username);
        final CustomerDto responseEntityBody = responseEntity.getBody();

        //Assert
        assertInstanceOf(CustomerDto.class, responseEntityBody);
        assertEquals("anil", responseEntityBody.getUsername());
    }

    @Test
    public void saveCustomer_validRequest_successReturn() {

        //Arrange
        final JwtResponse jwtResponse = new JwtResponse("anil", "1234");
        final CustomerDto customerDto = new CustomerDto();
        customerDto.setUsername("anil");

        when(customerService.save(customerDto)).thenReturn(jwtResponse);

        //Act
        final ResponseEntity<JwtResponse> responseEntity = customerController.saveCustomer(customerDto);
        final JwtResponse responseEntityBody = responseEntity.getBody();

        //Assert
        assertInstanceOf(JwtResponse.class, responseEntityBody);
        assertEquals("anil", responseEntityBody.getUsername());
        assertEquals("1234", responseEntityBody.getToken());
    }
}
