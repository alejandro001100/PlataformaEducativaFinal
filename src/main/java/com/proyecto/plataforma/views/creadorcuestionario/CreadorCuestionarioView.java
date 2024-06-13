package com.proyecto.plataforma.views.creadorcuestionario;

import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Creador Cuestionario")
@Route(value = "creador-cuestionario", layout = MainLayout.class)
public class CreadorCuestionarioView extends Composite<VerticalLayout> {

    public CreadorCuestionarioView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        TimePicker timePicker = new TimePicker();
        ComboBox comboBox = new ComboBox();
        Button buttonSecondary = new Button();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H6 h6 = new H6();
        Paragraph textSmall = new Paragraph();
        ComboBox comboBox2 = new ComboBox();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        ComboBox comboBox3 = new ComboBox();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        H6 h62 = new H6();
        Paragraph textSmall2 = new Paragraph();
        ComboBox comboBox4 = new ComboBox();
        TextField textField7 = new TextField();
        ComboBox comboBox5 = new ComboBox();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.START);
        timePicker.setLabel("Seleccione limite de tiempo");
        timePicker.setWidth("min-content");
        comboBox.setLabel("Seleccione su numero de preguntas");
        comboBox.setWidth("200px");
        setComboBoxSampleData(comboBox);
        buttonSecondary.setText("Enviar");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, buttonSecondary);
        buttonSecondary.setWidth("min-content");
        buttonSecondary.setHeight("40px");
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn2.setAlignItems(Alignment.START);
        h6.setText("Pregunta 1");
        h6.setWidth("max-content");
        textSmall.setText("(Con un for determinar los combobox y textfields con el numero de preguntas seleccionados)");
        textSmall.setWidth("100%");
        textSmall.setHeight("20px");
        textSmall.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        comboBox2.setLabel("Seleccione su tipo de pregunta");
        comboBox2.setWidth("300px");
        comboBox2.setHeight("50px");
        setComboBoxSampleData(comboBox2);
        textField.setLabel("Escriba la pregunta");
        textField.setWidth("min-content");
        textField2.setLabel("Opcion 1 (\"Con un for determinar la cantidad de opciones\")");
        textField2.setWidth("450px");
        textField3.setLabel("Opcion 2 (\"Con un for determinar la cantidad de opciones\")");
        textField3.setWidth("450px");
        textField4.setLabel("Opcion 3 (\"Con un for determinar la cantidad de opciones\")");
        textField4.setWidth("450px");
        comboBox3.setLabel(
                "Selecciona la respuesta correcta a tu pregunta(\"Escribir el numero de pregunta con el for\")");
        comboBox3.setWidth("600px");
        setComboBoxSampleData(comboBox3);
        layoutColumn3.getStyle().set("flex-grow", "1");
        textField5.setLabel("Título");
        textField5.setWidth("min-content");
        textField6.setLabel("Descripción");
        textField6.setWidth("min-content");
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        layoutColumn4.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn4.setAlignItems(Alignment.START);
        h62.setText("Pregunta 2");
        h62.setWidth("max-content");
        textSmall2.setText("(Ejemplo con verdadero o falso)");
        textSmall2.setWidth("100%");
        textSmall2.setHeight("20px");
        textSmall2.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        comboBox4.setLabel("Seleccione su tipo de pregunta");
        comboBox4.setWidth("300px");
        comboBox4.setHeight("50px");
        setComboBoxSampleData(comboBox4);
        textField7.setLabel("Escribe tu pregunta (\"Escribir el numero de pregunta con el for\")");
        textField7.setWidth("450px");
        comboBox5.setLabel(
                "Selecciona la respuesta correcta a tu pregunta(\"Escribir el numero de pregunta con el for\")");
        comboBox5.setWidth("600px");
        setComboBoxSampleData(comboBox5);
        layoutColumn5.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth("100%");
        layoutColumn5.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Crear");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutRow);
        layoutRow.add(timePicker);
        layoutRow.add(comboBox);
        layoutRow.add(buttonSecondary);
        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(h6);
        layoutColumn2.add(textSmall);
        layoutColumn2.add(comboBox2);
        layoutColumn2.add(textField);
        layoutColumn2.add(textField2);
        layoutColumn2.add(textField3);
        layoutColumn2.add(textField4);
        layoutColumn2.add(comboBox3);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(textField5);
        layoutColumn3.add(textField6);
        getContent().add(layoutColumn4);
        layoutColumn4.add(h62);
        layoutColumn4.add(textSmall2);
        layoutColumn4.add(comboBox4);
        layoutColumn4.add(textField7);
        layoutColumn4.add(comboBox5);
        getContent().add(layoutColumn5);
        layoutColumn5.add(buttonPrimary);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}