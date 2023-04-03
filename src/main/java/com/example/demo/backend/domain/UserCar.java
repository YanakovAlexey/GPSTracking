package com.example.demo.backend.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "user_car")
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCar extends PersistentObject {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user;

    @Column(name = "carId")
    long carId;


}
