package com.example.demo.backend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class SideBarView extends HorizontalLayout {

//    private final Div trackContainer;
//    private final Div carContainer;
//    private final Div userContainer;
    public SideBarView() {
        screen();
    }
    public void screen() {
        Button showButton = new Button("Показать");
        showButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });
        showButton.addClassNames("show-button");

        Button createButton = new Button("Создать");
        createButton.addClickListener(event -> {
//            enterButton.getUI().ifPresent(ui -> ui.navigate("/"));
        });
        createButton.addClassNames("create-button");

        addClassNames("sideBar");
        add(showButton, createButton);
    }
}
