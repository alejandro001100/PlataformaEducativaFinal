package com.proyecto.plataforma.views.creadorcurso;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.CursosLista;
import com.proyecto.plataforma.views.MainLayout;
import com.proyecto.plataforma.views.gestorclases.GestorClasesView;
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
        cursosLista.cargarCursos();

        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        TextField textFieldTitulo = new TextField();
        ComboBox<SampleItem> comboBoxArea = new ComboBox<>();
        TextArea textAreaResumen = new TextArea();
        Button buttonPrimary = new Button();

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

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

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
            } else if ( titulo.isEmpty () || area.isEmpty () || resumen.isEmpty () ) {
                Notification.show("Llene todos los campos");
            } else {
                Cursos cursos = new Cursos(null, titulo, resumen, area);
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
