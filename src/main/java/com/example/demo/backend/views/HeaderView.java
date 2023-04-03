package com.example.demo.backend.views;

import com.example.demo.backend.service.Impl.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
public class HeaderView extends HorizontalLayout {
    private final AuthenticatedUser authenticatedUser;
    public HeaderView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        screen();
    }
    public void screen() {
        Button authButton = new Button("Вход в систему");
        authButton.addClassNames("auth-button");

        authButton.addClickListener(event -> {
            authButton.getUI().ifPresent(ui -> ui.navigate("/auth"));
        });

        this.addClassNames("view-header");
        this.add(createLogo(), authButton);


        //        UI.getCurrent().getPage()
//                .executeJs(
//                        String.format("console.log('parent class ', '%s')",
//                                mainLayout != null ? mainLayout.getClass() : "'NOT_PRESENT'")
//                );
//        if (mainLayout != null) {
//            UI.getCurrent().getPage()
//                    .executeJs(
//                            String.format("console.log('header class ', '%s')", mainLayout.getHeaderView())
//                    );
//            mainLayout.getHeaderView().showButtons();
//        }
    }
    public Div createLogo() {
        Div container = new Div();
        Anchor refresh = new Anchor("/", new Image("https://i.ibb.co/c112R87/gps-LOGOBlue.png", "My Alt Image"));
        refresh.addClassNames("logo");
        container.add(refresh);
        return container;
    }

//        public void showButtons() {
//
//        UI.getCurrent().getPage()
//                .executeJs(
//                        String.format("console.log('show buttons ', '%s')",
//                                this.containerBtn.getClassNames().stream().collect(Collectors.joining(" ,"))
//                        )
//                );
//        this.containerBtn.removeClassName("mhidden");
//        UI.getCurrent().getPage()
//                .executeJs(
//                        String.format("console.log('after hidden ', '%s')",
//                                this.containerBtn.getClassNames().stream().collect(Collectors.joining(" ,"))
//                        )
//                );
//    }
}
