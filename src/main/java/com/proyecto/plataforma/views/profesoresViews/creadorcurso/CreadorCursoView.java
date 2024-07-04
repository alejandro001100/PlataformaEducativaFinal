package com.proyecto.plataforma.views.profesoresViews.creadorcurso;

import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.estructuras.CursosLista;
import com.proyecto.plataforma.capaNegocio.Profesor;
import com.proyecto.plataforma.estructuras.ProfesorLista;
import com.proyecto.plataforma.views.MainLayout;
import com.proyecto.plataforma.views.profesoresViews.gestorclases.GestorClasesView;
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
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Creador Curso")
@Route(value = "creador-curso", layout = MainLayout.class)
public class CreadorCursoView extends Composite<VerticalLayout> {

    private final CursosLista cursosLista;
    private final ProfesorLista profesorLista;

    @Autowired
    public CreadorCursoView(CursosLista cursosLista, ProfesorLista profesorLista) {
        this.cursosLista = cursosLista;
        this.profesorLista = profesorLista;
        cursosLista.cargarCursos();

        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        TextField textFieldTitulo = new TextField();
        ComboBox<SampleItem> comboBoxArea = new ComboBox<>();
        TextField textFieldTema = new TextField(); // Campo de texto para el tema
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

        textFieldTema.setLabel("Tema");
        textFieldTema.setWidth("min-content");

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
        layoutColumn2.add(textFieldTema);
        layoutColumn2.add(textAreaResumen);
        layoutColumn2.add(buttonPrimary);

        buttonPrimary.addClickListener(e -> {

            String titulo = textFieldTitulo.getValue();
            String area = comboBoxArea.getValue() != null ? comboBoxArea.getValue().label() : "";
            String tema = textFieldTema.getValue();
            String resumen = textAreaResumen.getValue();
            Profesor currentUser = VaadinSession.getCurrent().getAttribute(Profesor.class);

            if (cursosLista.isTituloRegistrado(titulo)) {
                Notification.show("El título del curso ya está registrado", 3000, Notification.Position.MIDDLE);
            } else if (titulo.isEmpty() || area.isEmpty() || tema.isEmpty() || resumen.isEmpty()) {
                Notification.show("Llene todos los campos", 3000, Notification.Position.MIDDLE);
            } else if (currentUser == null) {
                Notification.show("No se pudo encontrar el usuario actual", 3000, Notification.Position.MIDDLE);
            } else {
                Cursos cursos = new Cursos(null, titulo, resumen, area, tema, currentUser.getNombre());
                cursosLista.guardarCursos(cursos);
                currentUser.addCursoCreado(cursos);
                profesorLista.agregarProfesor(currentUser);  // Usamos ProfesorLista para guardar el profesor actualizado
                Notification.show("Curso guardado con éxito", 3000, Notification.Position.MIDDLE);
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
//Final version

