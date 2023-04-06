package com.example.demo.backend.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Table(name = "location")
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location extends PersistentObject {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId")
    Car car;
    @Column(name = "lat")
    double lat;
    @Column(name = "lon")
    double lon;

    @Column(name = "measure_time")
    ZonedDateTime measureTime;
}
