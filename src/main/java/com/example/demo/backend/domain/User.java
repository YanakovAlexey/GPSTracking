package com.example.demo.backend.domain;

import com.example.demo.backend.domain.enums.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "users")
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class User extends PersistentObject {
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "email")
    String email;
    @Column(name = "phone")
    String phone;
    @Column(name = "role")
    Roles role;
}
