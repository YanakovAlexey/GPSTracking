package com.example.demo;


import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.views.HeaderView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AnonymousAllowed
@Route("/")
@Component
@UIScope
public class MainLayout extends VerticalLayout implements RouterLayout {

    private final HeaderView headerView;
    //private final ContentView contentView;
    private final AuthenticatedUser authenticatedUser;

    private final Label label = new Label("" +
            "Следите за местоположением Вашего " +
            "автомобиля в режиме реального времени " +
            "с системой GPS Tracking System.");

    @Autowired
    public MainLayout(HeaderView headerView, AuthenticatedUser authenticatedUser) {
        this.headerView = headerView;
        this.authenticatedUser = authenticatedUser;
        //this.contentView = new ContentView();
        this.addClassNames("main-view");
        this.label.addClassNames("content-label");
        setPadding(false);

        add(this.headerView);
    }

}
