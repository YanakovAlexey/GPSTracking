package com.example.demo.component.view;

import com.example.demo.backend.service.CarService;
import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.views.HeaderView;
import com.example.demo.ui.OSMMapView;
import com.example.demo.utils.MapJSUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@UIScope
public class MapView extends VerticalLayout {
    private boolean viewLunch = false;
    private Button btnCenter = new Button("Показать");
    private OSMMapView mapView;
    private final AuthenticatedUser authenticatedUser;
    private final HeaderView headerView;

    @Autowired
    public MapView(AuthenticatedUser authenticatedUser, HeaderView headerView) {
        this.authenticatedUser = authenticatedUser;
        this.headerView = headerView;
        this.setPadding(false);
        this.addClassNames("body");
        this.headerView.visibleButtonAuth();

        this.btnCenter.addClickListener(e -> {
            final Random rnd = new Random();
            final List<MapJSUtil.Coordinate> coordinates = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                final double lat = rnd.nextDouble(-12.0, 54.0);
                final double lon = rnd.nextDouble(22.0, 44.0);
                MapJSUtil.Coordinate coordinate = new MapJSUtil.Coordinate(lat, lon);
                coordinates.add(coordinate);
            }
            mapView.addPolyline(coordinates);
        });
        mapView = new OSMMapView();
        mapView.addClassNames("map");

        this.add(mapView);
        this.setSizeFull();
    }

    public void addRoute(List<MapJSUtil.Coordinate> coordinates) {
        mapView.addPolyline(coordinates);
    }
}