package com.proyecto.plataforma.views.restablecerpassword;

import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Restablecer Password")
@Route(value = "restablecer-password", layout = MainLayout.class)
public class RestablecerPasswordView extends Composite<VerticalLayout> {

    public RestablecerPasswordView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        Paragraph textSmall = new Paragraph();
        EmailField emailField = new EmailField();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h3.setText("Restablecer tu contraseña");
        h3.setWidth("max-content");
        textSmall.setText(
                "Introduce tu dirección de correo electrónico y te enviaremos un enlace para que restablezcas la contraseña.");
        textSmall.setWidth("100%");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        emailField.setLabel("Correo electrónico");
        emailField.setWidth("min-content");
        buttonPrimary.setText("Restablecer contraseña");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(textSmall);
        layoutColumn2.add(emailField);
        layoutColumn2.add(buttonPrimary);
    }
}
