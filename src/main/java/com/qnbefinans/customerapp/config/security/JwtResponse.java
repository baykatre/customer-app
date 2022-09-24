package com.qnbefinans.customerapp.config.security;

import lombok.Getter;

@Getter
public class JwtResponse {

    private final String username;

    private final String token;

    public JwtResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

}
