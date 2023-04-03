package com.example.demo.backend.views;

import com.example.demo.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(MainLayout.class)
public class ContentView extends Div implements RouterLayout {
    public ContentView() {
        this.addClassNames("content-view");
    }
}