package com.qnbefinans.customerapp.config.security;

import com.qnbefinans.customerapp.customer.CustomerRepository;
import com.qnbefinans.customerapp.customer.model.Customer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public JwtUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Customer customer = customerRepository
                .findById(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username)
                );
        return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
    }
}