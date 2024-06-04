package com.proyecto.plataforma.views.registro;

import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Registro")
@Route(value = "registro", layout = MainLayout.class)
public class RegistroView extends Composite<VerticalLayout> {

    public RegistroView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H3 h3 = new H3();
        H6 h6 = new H6();
        EmailField emailField = new EmailField();
        PasswordField passwordField = new PasswordField();
        Select select = new Select();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.CENTER);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        h3.setText("Regístrate");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, h3);
        h3.setWidth("max-content");
        h6.setText(
                "Al continuar, aceptas nuestro Acuerdo del usuario y confirmas que has entendido la Política de privacidad.");
        h6.setWidth("max-content");
        emailField.setLabel("Correo electrónico");
        emailField.setWidth("min-content");
        passwordField.setLabel("Contraseña");
        passwordField.setWidth("min-content");
        select.setLabel("¿Eres?");
        select.setWidth("min-content");
        setSelectSampleData(select);
        buttonPrimary.setText("Continuar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutRow);
        layoutRow.add(h3);
        getContent().add(h6);
        getContent().add(emailField);
        getContent().add(passwordField);
        getContent().add(select);
        getContent().add(buttonPrimary);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setSelectSampleData(Select select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }
}
