package com.example.demo.backend.views;

import com.example.demo.component.view.CarRoutesView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class SideBarView extends HorizontalLayout {
    public Div carsInfoContainer;

    public SideBarView(CarRoutesView carRoutesView) {
        carsInfoContainer();
        this.add(carRoutesView);
        this.addClassNames("side-bar");
    }
    public void carsInfoContainer() {
        carsInfoContainer = new Div();
        Button createButton = new Button("Создать");
        createButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });
        createButton.addClassNames("create-track-button");

        Label brandLabel = new Label("Марка:");
        Label showBrandLabel = new Label("getBrand");
        Label modelLabel = new Label("Модель:");
        Label showModelLabel = new Label("GetModel");
        Label registrationNumberLabel = new Label("ГОС-НОМЕР:");
        Label showRegistrationNumberLabel = new Label("GetRegistrationNumber");

        brandLabel.addClassNames("brandLabel", "j");
        showBrandLabel.addClassNames("showBrandLabel");
        modelLabel.addClassNames("modelLabel", "j");
        showModelLabel.addClassNames("showModelLabel");
        registrationNumberLabel.addClassNames("registrationNumberLabel", "j");
        showRegistrationNumberLabel.addClassNames("showRegistrationNumberLabel");

        carsInfoContainer.addClassNames("carsInfoContainer");
        carsInfoContainer.add(
                brandLabel, showBrandLabel,
                modelLabel, showModelLabel,
                registrationNumberLabel, showRegistrationNumberLabel, createButton);

        ListBox carsListBox = new ListBox();
        carsListBox.addClassNames("carsList");
        carsListBox.add(carsInfoContainer);
        this.add(carsListBox);
    }
}