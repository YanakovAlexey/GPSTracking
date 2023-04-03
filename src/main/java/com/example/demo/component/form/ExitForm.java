package com.example.demo.component.form;

import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.views.ContentView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;


@Route(value = "exit", layout = ContentView.class)
@RolesAllowed("ROLE_USER")
public class ExitForm extends Div {

    private final AuthenticatedUser authenticatedUser;
    public Button exitButton = new Button("Выйти");

    public ExitForm(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;

        exitButton.addClassNames("exit-button");
        exitButton.addClickListener(event -> {
            exitButton.getUI().ifPresent(ui -> ui.navigate("exit"));
            this.authenticatedUser.logout();
        });


        add(exitButton);
    }
}
