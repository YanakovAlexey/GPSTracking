package com.example.demo.backend.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "cars")
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Car extends PersistentObject {

    @Column(name = "brand")
    String brand;
    @Column(name = "model")
    String model;
    @Column(name = "registrationNumber")
    String registrationNumber;
}
