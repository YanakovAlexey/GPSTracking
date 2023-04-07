package com.example.demo.component.form;

import com.example.demo.backend.service.Impl.AccountServiceImpl;
import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.example.demo.backend.views.ContentView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Авторизация")
@Route(value = "auth", layout = ContentView.class)
public class AuthForm extends FormLayout implements BeforeEnterObserver {
    private final TextField loginTF;
    private final PasswordField passwordField;
    private final Button enterButton;
    private final AuthFormViewModel state = new AuthFormViewModel();
    private final AccountServiceImpl accountService;
    private final AuthenticatedUser authenticatedUser;
    private final Anchor regLink = new Anchor("/create-user", "Регистрация");

    public AuthForm(AccountServiceImpl accountService, AuthenticatedUser authenticatedUser) {

        this.accountService = accountService;
        this.authenticatedUser = authenticatedUser;

        this.loginTF = createLoginField();
        this.passwordField = createPasswordField();
        this.enterButton = createEnterButton();

        enterButton.addClickListener(event -> {
            if (authenticatedUser.get().isPresent()) {
                enterButton.getUI().ifPresent(ui ->
                        ui.navigate("main"));

            }
        });

        enterButton.addClassNames("enter-button");
        loginTF.addClassNames("login-txtBox");
        passwordField.addClassNames("password-txtBox");
        addClassNames("authorization-view");

        loginTF.setLabel("Введите логин:");
        passwordField.setLabel("Введите пароль:");
        regLink.addClassNames("reg-link");

        add(loginTF, passwordField, enterButton, regLink);
    }

    private TextField createLoginField() {
        if (this.loginTF != null)
            return this.loginTF;

        TextField login = new TextField();
        login.setPlaceholder("Логин");
        //login.setPattern("^(?=.{5,32}$)[^@]*@[^@]*$");
        login.addValueChangeListener((event) -> {
            if (event.getValue() == null) {
                return;
            }
            state.login = event.getValue();
        });
        return login;
    }

    private PasswordField createPasswordField() {
        if (this.passwordField != null)
            return this.passwordField;

        PasswordField password = new PasswordField();
        password.setPlaceholder("Пароль");
        password.addValueChangeListener((event) -> {
            if (event.getValue() != null) {
                state.password = event.getValue();
            }
        });
        return password;
    }

    private Button createEnterButton() {
        if (this.enterButton != null)
            return this.enterButton;

        Button button = new Button("Войти");
        button.addClickListener((event) -> {
            authenticate();
        });
        return button;
    }

    private Label createLabel() {
        Label label = new Label();
        return label;
    }

    private void authenticate() {
        try {
            accountService.authenticate(state.login, state.password);
            Notification.show("Успешно").open();
        } catch (Exception e) {
            Notification.show(e.getMessage()).open();
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {

            String username = authenticatedUser.get().toString();
            //  this.label.setText("Вы авторизованы как " + username);
        }
    }

    private static class AuthFormViewModel {
        String login = "";
        String password = "";
    }
}
