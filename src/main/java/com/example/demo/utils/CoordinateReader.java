package com.example.demo.utils;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.domain.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CoordinateReader {

    public static List<Location> readCoordinatesForCar(Car car, String filePath) {
        final Path path = Path.of(filePath);
        if (Files.notExists(path)) {
            return List.of();
        }
        final File file = path.toFile();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SolomonPoint[] points = new SolomonPoint[0];
        try {
            points = mapper.readValue(file, SolomonPoint[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Stream.of(points)
                .map(sp -> {
                    if (sp.latLon == null)
                        return null;
                    if (sp.latLon.length != 2)
                        return null;
                    if (sp.time == null)
                        return null;
                    Location location = new Location();
                    location.setCar(car);
                    location.setLat(sp.latLon[0]);
                    location.setLon(sp.latLon[1]);
                    location.setMeasureTime(ZonedDateTime.ofInstant(sp.time.toInstant(), ZoneOffset.UTC));
                    return location;
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private static class SolomonPoint {
        @JsonProperty("latlon")
        double[] latLon;
        @JsonProperty("time")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date time;
    }
}
