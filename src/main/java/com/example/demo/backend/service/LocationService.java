package com.example.demo.backend.service;

import com.example.demo.backend.domain.Location;
import com.example.demo.utils.MapJSUtil;

import java.time.ZonedDateTime;
import java.util.List;

public interface LocationService {

    void save(Location location);

    List<Location> getAllLocations();

    List<MapJSUtil.Coordinate> getAllByCar(long carId, ZonedDateTime start, ZonedDateTime end);

    void loadLocations(long carId, String filePath);
}
