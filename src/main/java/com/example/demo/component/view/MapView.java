package com.example.demo.component.view;

import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.ui.OSMMapView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class MapView extends VerticalLayout {
    private boolean viewLunch = false;
    private Button btnCenter = new Button("Показать");
    private OSMMapView mapView;
    AuthenticatedUser authenticatedUser;
    public MapView() {
        this.setPadding(false);
        this.addClassNames("body");

        this.btnCenter.addClickListener(e -> {
            mapView.addPolyline(List.of());
        });
        mapView = new OSMMapView();
        mapView.addClassNames("map");

        this.add(mapView);
        this.setSizeFull();
    }
}