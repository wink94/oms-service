package com.windula.oms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user_address")
public class UserAddress {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_address_id", unique=true, nullable=false, precision=10)
    private int userAddressId;
    @Column(name="user_id", unique=true, nullable=false)
    private int userId;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="street_number")
    private String streetNumber;
    @Column(name="street")
    private String street;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="country")
    private String country;
}
