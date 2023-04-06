package com.example.demo.backend.service.Impl;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.domain.Location;
import com.example.demo.backend.repository.CarRepository;
import com.example.demo.backend.repository.LocationRepository;
import com.example.demo.backend.service.LocationService;
import com.example.demo.utils.CoordinateReader;
import com.example.demo.utils.MapJSUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final CarRepository carRepository;

    public LocationServiceImpl(LocationRepository locationRepository, CarRepository carRepository) {
        this.locationRepository = locationRepository;
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public void save(Location location) {
        locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public List<MapJSUtil.Coordinate> getAllByCar(long carId, ZonedDateTime start, ZonedDateTime end) {

        Optional<Car> car = carRepository.findById(carId);
        if (car.isEmpty()) {
            throw new RuntimeException("Такого автомобиля не существует");
        }

        if (start.isAfter(end)) {
            throw new RuntimeException("Дата начала должна быть больше даты конца");
        }

        var locations = locationRepository
                .searchByCarAndMeasureTimeBetween(car.get(), start, end)
                .stream()
                .map(location -> new MapJSUtil.Coordinate(location.getLat(), location.getLon()))
                .toList();


        return locations;
    }

    @Override
    public void loadLocations(long carId, String filePath) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isEmpty()) {
            throw new RuntimeException("Такого автомобиля не существует");
        }
        var locations = CoordinateReader.readCoordinatesForCar(car.get(), filePath);
        locationRepository.saveAll(locations);
    }

}

