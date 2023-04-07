package com.example.demo.backend.views;

import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class HeaderView extends HorizontalLayout {
    private final AuthenticatedUser authenticatedUser;
    public Button authButton = new Button("Вход в систему");
    private Button messageButton = new Button("Сообщение");

    public HeaderView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        screen();
        visibleButtonAuth();
        visibleButtonMessage();
    }

    public void screen() {
        authButton.addClassNames("auth-button");
        messageButton.addClassNames("message-button");

        this.addClassNames("view-header");

        this.add(createLogo(), messageButton, authButton);
    }

    public void visibleButtonAuth() {

        if (authenticatedUser.get().isEmpty()) {
            authButton.setText("Вход в систему");
            authButton.addClickListener(event -> {
//                authButton.setVisible(false);

                authButton.getUI().ifPresent(ui ->
                        ui.navigate("/auth"));
            });

        } else {
            authButton.setText("Выход");
            authButton.addClickListener(event -> {
                authenticatedUser.logout();
            });
        }
    }

    public void visibleButtonMessage() {
        if (authenticatedUser.get().isEmpty()) {
            messageButton.setVisible(false);

        } else {
            messageButton.setVisible(true);
            messageButton.addClickListener(event -> {
                messageButton.getUI().ifPresent(ui ->
                        ui.navigate("/message"));
            });
        }

    }


    public Div createLogo() {
        Div container = new Div();
        Anchor refresh = new Anchor("/",
                new Image("https://i.ibb.co/c112R87/gps-LOGOBlue.png", "My Alt Image"));
        refresh.addClassNames("logo");
        container.add(refresh);
        return container;
    }
}
