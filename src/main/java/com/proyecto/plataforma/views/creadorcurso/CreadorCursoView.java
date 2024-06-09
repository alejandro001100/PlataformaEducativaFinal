package com.proyecto.plataforma.views.creadorcurso;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.CursosLista;
import com.proyecto.plataforma.views.MainLayout;
import com.proyecto.plataforma.views.gestorclases.GestorClasesView;
import com.proyecto.plataforma.views.login.LoginView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Creador Curso")
@Route(value = "creador-curso", layout = MainLayout.class)
public class CreadorCursoView extends Composite<VerticalLayout> {

    private final CursosLista cursosLista;

    public CreadorCursoView(CursosLista cursosLista) {
        this.cursosLista = cursosLista;


        /*
        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonTertiary = new Button();
        Button buttonTertiary2 = new Button();
        Button buttonTertiary3 = new Button();
        Button buttonTertiary4 = new Button();
        Button buttonTertiary5 = new Button();
        Button buttonTertiary6 = new Button();
        Button buttonTertiary7 = new Button();
        Button buttonTertiary8 = new Button();
        Button buttonTertiary9 = new Button();
        Button buttonTertiary10 = new Button();
        Button buttonTertiary11 = new Button();
        Button buttonTertiary12 = new Button();
        Button buttonTertiary13 = new Button();
        Button buttonTertiary14 = new Button();
        Button buttonTertiary15 = new Button();
        Button buttonTertiary16 = new Button();
        Button buttonTertiary17 = new Button();
        Button buttonTertiary18 = new Button();
        Button buttonTertiary19 = new Button();
        Button buttonTertiary20 = new Button();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        Hr hr = new Hr();
        H3 h3 = new H3();
        Paragraph textMedium = new Paragraph();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        ComboBox comboBoxArea = new ComboBox();
        TextField textFieldTitulo = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        buttonTertiary.setText("<");
        buttonTertiary.setWidth("min-content");
        buttonTertiary.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary2.setText(">");
        buttonTertiary2.setWidth("min-content");
        buttonTertiary2.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary3.setText("B");
        buttonTertiary3.setWidth("min-content");
        buttonTertiary3.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary4.setText("I");
        buttonTertiary4.setWidth("min-content");
        buttonTertiary4.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary5.setText("U");
        buttonTertiary5.setWidth("min-content");
        buttonTertiary5.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary6.setText("T");
        buttonTertiary6.setWidth("min-content");
        buttonTertiary6.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary7.setText("H1");
        buttonTertiary7.setWidth("min-content");
        buttonTertiary7.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary8.setText("h2");
        buttonTertiary8.setWidth("min-content");
        buttonTertiary8.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary9.setText("h3");
        buttonTertiary9.setWidth("min-content");
        buttonTertiary9.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary10.setText("X2");
        buttonTertiary10.setWidth("min-content");
        buttonTertiary10.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary11.setText("X2");
        buttonTertiary11.setWidth("min-content");
        buttonTertiary11.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary12.setText("\"");
        buttonTertiary12.setWidth("min-content");
        buttonTertiary12.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary13.setText("<>");
        buttonTertiary13.setWidth("min-content");
        buttonTertiary13.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary14.setText("A");
        buttonTertiary14.setWidth("min-content");
        buttonTertiary14.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary15.setText("B");
        buttonTertiary15.setWidth("min-content");
        buttonTertiary15.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary16.setText("C");
        buttonTertiary16.setWidth("min-content");
        buttonTertiary16.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary17.setText("0");
        buttonTertiary17.setWidth("min-content");
        buttonTertiary17.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary18.setText("1");
        buttonTertiary18.setWidth("min-content");
        buttonTertiary18.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary19.setText("2");
        buttonTertiary19.setWidth("min-content");
        buttonTertiary19.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary20.setText("3");
        buttonTertiary20.setWidth("min-content");
        buttonTertiary20.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn3.setWidth("900px");
        layoutColumn3.getStyle().set("flex-grow", "1");
        h3.setText("(Nombre Clase)");
        h3.setWidth("max-content");
        textMedium.setText("Resumen de la Clase");
        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        layoutColumn5.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth("100%");
        layoutColumn5.getStyle().set("flex-grow", "1");
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        layoutColumn4.getStyle().set("flex-grow", "1");
        comboBoxArea.setLabel("Área");
        comboBoxArea.setWidth("min-content");
        setComboBoxSampleData(comboBoxArea);
        textFieldTitulo.setLabel("Clase");
        textFieldTitulo.setWidth("min-content");
        textField2.setLabel("Tema");
        textField2.setWidth("min-content");
        textField3.setLabel("Descripción");
        textField3.setWidth("min-content");
        buttonSecondary.setText("Crear");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutRow);
        layoutRow.add(layoutRow2);
        layoutRow2.add(buttonTertiary);
        layoutRow2.add(buttonTertiary2);
        layoutRow2.add(buttonTertiary3);
        layoutRow2.add(buttonTertiary4);
        layoutRow2.add(buttonTertiary5);
        layoutRow2.add(buttonTertiary6);
        layoutRow2.add(buttonTertiary7);
        layoutRow2.add(buttonTertiary8);
        layoutRow2.add(buttonTertiary9);
        layoutRow2.add(buttonTertiary10);
        layoutRow2.add(buttonTertiary11);
        layoutRow2.add(buttonTertiary12);
        layoutRow2.add(buttonTertiary13);
        layoutRow2.add(buttonTertiary14);
        layoutRow2.add(buttonTertiary15);
        layoutRow2.add(buttonTertiary16);
        layoutRow2.add(buttonTertiary17);
        layoutRow2.add(buttonTertiary18);
        layoutRow2.add(buttonTertiary19);
        layoutRow2.add(buttonTertiary20);
        getContent().add(layoutRow3);
        layoutRow3.add(layoutColumn2);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(hr);
        layoutColumn3.add(h3);
        layoutColumn3.add(textMedium);
        layoutColumn3.add(layoutColumn5);
        layoutColumn3.add(layoutRow4);
        layoutRow3.add(layoutColumn4);
        layoutColumn4.add(comboBoxArea);
        layoutColumn4.add(textFieldTitulo);
        layoutColumn4.add(textField2);
        layoutColumn4.add(textField3);
        layoutColumn4.add(buttonSecondary);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBoxArea) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBoxArea.setItems(sampleItems);
        comboBoxArea.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }*/


        cursosLista.cargarCursos();
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        TextField textFieldTitulo = new TextField();
        ComboBox<SampleItem> comboBoxArea = new ComboBox<>();
        TextArea textAreaResumen = new TextArea();
        Button buttonPrimary = new Button();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        layoutColumn2.setAlignItems(FlexComponent.Alignment.CENTER);

        h1.setText("Nuevo curso");
        h1.setWidth("max-content");

        textFieldTitulo.setLabel("Título del curso");
        textFieldTitulo.setWidth("min-content");

        comboBoxArea.setLabel("Área");
        comboBoxArea.setWidth("min-content");
        setComboBoxSampleData(comboBoxArea);

        textAreaResumen.setLabel("Resumen");
        textAreaResumen.setWidth("100%");

        buttonPrimary.setText("Guardar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(textFieldTitulo);
        layoutColumn2.add(comboBoxArea);
        layoutColumn2.add(textAreaResumen);
        layoutColumn2.add(buttonPrimary);

        buttonPrimary.addClickListener(e -> {
            String titulo = textFieldTitulo.getValue();
            String area = comboBoxArea.getValue() != null ? comboBoxArea.getValue().label() : "Área no seleccionada";
            String resumen = textAreaResumen.getValue();

            if(cursosLista.isTituloRegistrado(titulo)) {
                Notification.show("El título del curso ya está registrado", 3000, Notification.Position.MIDDLE);
                return;
            }else {
                Cursos cursos = new Cursos(titulo, resumen, area);
                cursosLista.guardarCursos(cursos);
                Notification.show("Curso guardado con éxito!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(GestorClasesView.class));
            }
        });
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox<SampleItem> comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("reciclaje_casa", "Reciclaje desde casa", null));
        sampleItems.add(new SampleItem("leyes_reciclaje", "Leyes en cuanto al reciclaje", null));
        sampleItems.add(new SampleItem("impacto_ambiental", "Impacto ambiental del reciclaje", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(SampleItem::label);
    }
}
