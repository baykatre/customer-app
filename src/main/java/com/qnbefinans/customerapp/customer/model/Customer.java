package com.qnbefinans.customerapp.customer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    private String username;

    @Column
    private String password;
}
