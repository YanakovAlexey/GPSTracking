package com.example.demo.component.view;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.service.CarService;
import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.service.LocationService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
@UIScope()
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarRoutesView extends Div {

    private Select<Car> carSelect;
    private final DatePicker departureDatePicker;
    private final DatePicker returnDatePicker;
    private final Button createTrackBtn;

    private final Button createCarButton;
    private Label txtLabel;
    private final MapView mapView;

    private final CarService carService;
    private final LocationService locationService;
    private final CarRouteViewModel state;
    private final AuthenticatedUser authenticatedUser;

    CarRoutesView(MapView mapView,
                  CarService carService,
                  LocationService locationService, AuthenticatedUser authenticatedUser) {
        this.carService = carService;
        this.locationService = locationService;
        this.authenticatedUser = authenticatedUser;
        //todo change to get cars by user. get user from Authenticated user.
        var cars = carService.getCarsByUserId(authenticatedUser.get().get().getId());
        this.state = new CarRouteViewModel(cars);

        this.mapView = mapView;

        this.carSelect = createCarSelect();
        this.departureDatePicker = createDepartureDatePicker();
        this.returnDatePicker = createReturnDatePicker();
        this.createTrackBtn = createTrackButton();
        this.txtLabel = createTxtLabel();
        this.createCarButton = createCarButton();

        this.addClassNames("carRoutesView");
        this.add(txtLabel, carSelect, departureDatePicker, returnDatePicker, createTrackBtn, createCarButton());
    }

    private Button createCarButton() {
        Button createCarBtn = new Button("Добавить автомобиль");
        createCarBtn.addClickListener(event -> {
            createCarBtn.getUI().ifPresent(ui -> ui.navigate("/create-car"));

        });
        createCarBtn.addClassNames("create-track-button");
        return createCarBtn;
    }

    public Label createTxtLabel() {
        txtLabel = new Label("Создание маршрута");
        txtLabel.addClassNames("txtLabel");
        this.add(txtLabel);

        return txtLabel;
    }

    public Select<Car> createCarSelect() {

        Select<Car> carSelect = new Select<>();
        carSelect.setLabel("Выберите автомобиль");
        carSelect.setItems(state.cars);
        carSelect.setItemLabelGenerator(car ->
                String.format("%s %s(%s)", car.getBrand(), car.getModel(), car.getRegistrationNumber()));
        carSelect.addValueChangeListener(event -> {
            state.carId = event.getValue().getId();
        });
        return carSelect;
    }

    private Button createTrackButton() {
        if (this.createTrackBtn != null)
            return this.createTrackBtn;

        Button createBtn = new Button("Показать маршрут");
        createBtn.addClassNames("createTrackBtn");
        createBtn.addClickListener(e -> {
            if (state.carId == 0) {
                Notification.show("Select car!!!");
                return;
            }
            var coordinates = locationService.
                    getAllByCar(state.carId, convertDate(state.departureDate), convertDate(state.returnDate));
            mapView.addRoute(coordinates);
        });
        return createBtn;
    }

    private DatePicker createDepartureDatePicker() {
        if (this.departureDatePicker != null)
            return this.departureDatePicker;

        DatePicker departureDate = new DatePicker();
        departureDate.setLabel("Начальная дата");
        departureDate.addClassNames("departureDate");
        departureDate.setMax(state.departureDate);
        departureDate.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.departureDate = event.getValue();
        });
        return departureDate;
    }

    private DatePicker createReturnDatePicker() {
        if (this.returnDatePicker != null)
            return this.returnDatePicker;

        DatePicker returnDate = new DatePicker();
        returnDate.setLabel("Финальная дата");
        returnDate.addClassNames("returnDate");
        returnDate.setMin(state.returnDate);
        returnDate.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.returnDate = event.getValue();
        });
        return returnDate;
    }

    private ZonedDateTime convertDate(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault());
    }

    private static class CarRouteViewModel {
        final List<Car> cars;
        long carId = 0;
        LocalDate departureDate;
        LocalDate returnDate;

        CarRouteViewModel(List<Car> cars) {
            this.cars = cars;
        }


    }
}