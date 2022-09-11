package com.windula.oms.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id", unique=true, nullable=false, precision=10)
    private int userId;
    @Column(name="user_name", nullable=false, length=30)
    private String userName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="role")
    private String role;
}
