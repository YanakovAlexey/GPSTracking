package com.example.demo.backend.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "cars")
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car extends PersistentObject {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user;
    @Column(name = "brand")
    String brand;
    @Column(name = "model")
    String model;
    @Column(name = "registrationNumber")
    String registrationNumber;
}
