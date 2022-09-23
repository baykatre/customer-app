package com.qnbefinans.customerapp.customer.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer convertToCustomer(CustomerDto customerDto);

    CustomerDto convertToCustomerDto(Customer customerDto);

    List<Customer> convertToCustomerList(List<CustomerDto> customerDtoList);

    List<CustomerDto> convertToCustomerDtoList(List<Customer> customerList);

}
