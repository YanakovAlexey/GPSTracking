package com.example.demo.backend.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class FooterView extends HorizontalLayout {

    public FooterView() {
        this.addClassNames("view-footer");
    }
}
