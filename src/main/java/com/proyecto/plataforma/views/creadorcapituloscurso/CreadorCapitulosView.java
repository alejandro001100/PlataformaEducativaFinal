package com.proyecto.plataforma.views.creadorcapituloscurso;

import com.proyecto.plataforma.data.Capitulo;
import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.Cuestionario;
import com.proyecto.plataforma.services.CursosService;
import com.proyecto.plataforma.services.CuestionarioService;
import com.proyecto.plataforma.views.MainLayout;
import com.proyecto.plataforma.views.gestorclases.GestorClasesView;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Route(value = "creador-capitulos", layout = MainLayout.class)
public class CreadorCapitulosView extends VerticalLayout {

    private TextField capituloTituloField;
    private TextArea capituloContenidoField;
    private ComboBox<Cuestionario> cuestionarioComboBox;
    private Accordion capituloAccordion;
    private Button saveButton;
    private Button regresarButton;
    private Button updateButton;
    private final CursosService cursosService;
    private final CuestionarioService cuestionarioService;
    private Cursos cursoActual;
    private Capitulo capituloActual;

    @Autowired
    public CreadorCapitulosView(CursosService cursosService, CuestionarioService cuestionarioService) {
        this.cursosService = cursosService;
        this.cuestionarioService = cuestionarioService;

        String cursoId = (String) VaadinSession.getCurrent().getAttribute("cursoId");
        Optional<Cursos> optionalCurso = cursosService.findById(cursoId);

        if (!optionalCurso.isPresent()) {
            Notification.show("No se encontró el curso seleccionado");
            return;
        }

        cursoActual = optionalCurso.get();

        capituloTituloField = new TextField("Título del Capítulo");
        capituloContenidoField = new TextArea("Contenido del Capítulo");
        capituloContenidoField.setWidthFull();
        capituloContenidoField.setHeight("300px");

        List<Cuestionario> cuestionarios = cuestionarioService.findAll();
        cuestionarioComboBox = new ComboBox<>("Seleccionar Cuestionario");
        cuestionarioComboBox.setItems(cuestionarios);
        cuestionarioComboBox.setItemLabelGenerator(Cuestionario::getTitulo);

        saveButton = new Button("Guardar Capítulo", event -> guardarCapitulo());
        updateButton = new Button("Actualizar Capítulo", event -> actualizarCapitulo());
        updateButton.setVisible(false);  // Inicialmente oculto

        regresarButton = new Button("Regresar", event -> getUI().ifPresent(ui -> ui.navigate(GestorClasesView.class)));

        capituloAccordion = new Accordion();
        actualizarAccordion(cursoActual.getCapitulos());

        add(capituloTituloField, capituloContenidoField, cuestionarioComboBox, new HorizontalLayout(saveButton, updateButton, regresarButton), capituloAccordion);
    }

    private void guardarCapitulo() {
        String capituloTitulo = capituloTituloField.getValue();
        String capituloContenido = capituloContenidoField.getValue();
        Cuestionario seleccionadoCuestionario = cuestionarioComboBox.getValue();

        if (capituloTitulo.isEmpty() || capituloContenido.isEmpty() || seleccionadoCuestionario == null) {
            Notification.show("El título, contenido del capítulo y cuestionario no pueden estar vacíos");
            return;
        }

        Capitulo nuevoCapitulo = new Capitulo(capituloTitulo, capituloContenido);
        cursoActual.getCapitulos().add(nuevoCapitulo);
        cursosService.saveCursos(cursoActual);
        Notification.show("Capítulo y cuestionario asociados guardados exitosamente");

        capituloTituloField.clear();
        capituloContenidoField.clear();
        cuestionarioComboBox.clear();
        actualizarAccordion(cursoActual.getCapitulos());
    }

    private void actualizarCapitulo() {
        String capituloTitulo = capituloTituloField.getValue();
        String capituloContenido = capituloContenidoField.getValue();
        Cuestionario seleccionadoCuestionario = cuestionarioComboBox.getValue();

        if (capituloTitulo.isEmpty() || capituloContenido.isEmpty() || seleccionadoCuestionario == null) {
            Notification.show("El título, contenido del capítulo y cuestionario no pueden estar vacíos");
            return;
        }

        capituloActual.setTitulo(capituloTitulo);
        capituloActual.setDescripcion(capituloContenido);
        cursosService.saveCursos(cursoActual);
        Notification.show("Capítulo actualizado exitosamente");

        capituloTituloField.clear();
        capituloContenidoField.clear();
        cuestionarioComboBox.clear();
        saveButton.setVisible(true);
        updateButton.setVisible(false);
        actualizarAccordion(cursoActual.getCapitulos());
    }

    private void actualizarAccordion(List<Capitulo> capitulos) {
        // Eliminar todos los paneles del acordeón
        capituloAccordion.getChildren().forEach(component -> {
            if (component instanceof AccordionPanel) {
                capituloAccordion.remove((AccordionPanel) component);
            }
        });

        // Añadir los capítulos al acordeón
        for (Capitulo capitulo : capitulos) {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> editarCapitulo(capitulo));

            VerticalLayout content = new VerticalLayout();
            content.add(new TextField("Título", capitulo.getTitulo()));
            TextArea descripcion = new TextArea("Descripción");
            descripcion.setValue(capitulo.getDescripcion());
            descripcion.setWidthFull();
            content.add(descripcion, editButton);
            capituloAccordion.add(capitulo.getTitulo(), content);
        }
    }

    private void editarCapitulo(Capitulo capitulo) {
        capituloActual = capitulo;
        capituloTituloField.setValue(capitulo.getTitulo());
        capituloContenidoField.setValue(capitulo.getDescripcion());
        saveButton.setVisible(false);
        updateButton.setVisible(true);
    }
}
