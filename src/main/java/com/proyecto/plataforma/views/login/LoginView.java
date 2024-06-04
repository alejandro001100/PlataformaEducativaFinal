package com.proyecto.plataforma.views.login;

import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Login")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class LoginView extends Composite<VerticalLayout> {

    public LoginView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H3 h3 = new H3();
        H5 h5 = new H5();
        EmailField emailField = new EmailField();
        PasswordField passwordField = new PasswordField();
        RouterLink routerLink = new RouterLink();
        Button buttonPrimary = new Button();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Paragraph textSmall = new Paragraph();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.CENTER);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        h3.setText("¡Le damos la bienvenida a Plataforma de Aprendizaje UDLA!");
        h3.setWidth("max-content");
        h5.setText("Inicie sesión en su cuenta");
        h5.setWidth("max-content");
        emailField.setLabel("Correo electrónico");
        emailField.setWidth("min-content");
        passwordField.setLabel("Contraseña");
        passwordField.setWidth("min-content");
        routerLink.setText("¿Olvidó su contraseña?");
        routerLink.setRoute(LoginView.class);
        routerLink.setWidth("200px");
        buttonPrimary.setText("Iniciar Sesión");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutRow2.setAlignItems(Alignment.CENTER);
        layoutRow2.setJustifyContentMode(JustifyContentMode.CENTER);
        textSmall.setText("¿No tiene una cuenta?");
        textSmall.setWidth("140px");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        buttonSecondary.setText("Regístrese");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutRow);
        layoutRow.add(h3);
        getContent().add(h5);
        getContent().add(emailField);
        getContent().add(passwordField);
        getContent().add(routerLink);
        getContent().add(buttonPrimary);
        getContent().add(layoutRow2);
        layoutRow2.add(textSmall);
        layoutRow2.add(buttonSecondary);
    }
}
