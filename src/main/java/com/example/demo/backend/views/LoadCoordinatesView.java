package com.example.demo.backend.views;

import com.example.demo.backend.service.LocationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("/load")
@AnonymousAllowed
public class LoadCoordinatesView extends Div {
    private final TextField pathfield;
    private final TextField carIdField;

    private final Button load;
    private final LocationService locationService;

    public LoadCoordinatesView(LocationService locationService) {
        this.locationService = locationService;
        this.pathfield = new TextField("File Path");
        this.carIdField = new TextField("Car id");
        this.load = new Button("Load");

        this.load.addClickListener((event) -> {
            locationService.loadLocations(
                    Long.valueOf(this.carIdField.getValue()),
                    this.pathfield.getValue()
            );
        });

        this.add(carIdField, pathfield, load);
    }


}
