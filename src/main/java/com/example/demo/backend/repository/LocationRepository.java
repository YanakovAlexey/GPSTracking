package com.example.demo.backend.repository;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAll();

    Location save(Location location);

    List<Location> searchByCarAndMeasureTimeBetween(Car car, ZonedDateTime start, ZonedDateTime end);
}

