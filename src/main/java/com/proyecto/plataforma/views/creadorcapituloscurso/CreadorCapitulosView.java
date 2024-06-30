package com.proyecto.plataforma.views.creadorcapituloscurso;

import com.proyecto.plataforma.data.Capitulo;
import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.Cuestionario;
import com.proyecto.plataforma.data.Profesor;
import com.proyecto.plataforma.services.CursosService;
import com.proyecto.plataforma.services.CuestionarioService;
import com.proyecto.plataforma.services.ProfesorService;
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

import java.util.Optional;
import java.util.PriorityQueue;

@Route(value = "creador-capitulos", layout = MainLayout.class)
public class CreadorCapitulosView extends VerticalLayout {

    private TextField capituloTituloField;
    private TextArea capituloContenidoField;
    private TextField capituloPrioridadField;
    private ComboBox<Cuestionario> cuestionarioComboBox;
    private Accordion capituloAccordion;
    private Button saveButton;
    private Button regresarButton;
    private Button updateButton;
    private final CursosService cursosService;
    private final CuestionarioService cuestionarioService;
    private final ProfesorService profesorService;
    private Cursos cursoActual;
    private Capitulo capituloActual;

    @Autowired
    public CreadorCapitulosView(CursosService cursosService, CuestionarioService cuestionarioService, ProfesorService profesorService) {
        this.cursosService = cursosService;
        this.cuestionarioService = cuestionarioService;
        this.profesorService = profesorService;

        String cursoId = (String) VaadinSession.getCurrent().getAttribute("cursoId");
        Optional<Cursos> cursoOpt = cursosService.encontrarPorId(cursoId);

        if (cursoOpt.isEmpty()) {
            Notification.show("No se encontró el curso seleccionado");
            return;
        } else {
            this.cursoActual = cursoOpt.get();
        }

        capituloTituloField = new TextField("Título del Capítulo");
        capituloContenidoField = new TextArea("Contenido del Capítulo");
        capituloContenidoField.setWidthFull();
        capituloContenidoField.setHeight("300px");

        capituloPrioridadField = new TextField("Prioridad del Capítulo");
        cuestionarioComboBox = new ComboBox<>("Seleccionar Cuestionario");
        cuestionarioComboBox.setItems(cuestionarioService.findAll());
        cuestionarioComboBox.setItemLabelGenerator(Cuestionario::getTitulo);

        saveButton = new Button("Guardar Capítulo", event -> guardarCapitulo());
        updateButton = new Button("Actualizar Capítulo", event -> actualizarCapitulo());
        updateButton.setVisible(false);  // Inicialmente oculto

        regresarButton = new Button("Regresar", event -> getUI().ifPresent(ui -> ui.navigate(GestorClasesView.class)));

        capituloAccordion = new Accordion();
        actualizarAccordion(new PriorityQueue<>(cursoActual.getCapitulos()));

        add(capituloTituloField, capituloContenidoField, capituloPrioridadField, cuestionarioComboBox,
                new HorizontalLayout(saveButton, updateButton, regresarButton), capituloAccordion);
    }

    private void guardarCapitulo() {
        // Obtener los valores de los campos de entrada
        String capituloTitulo = capituloTituloField.getValue();
        String capituloContenido = capituloContenidoField.getValue();
        int capituloPrioridad;

        try {
            capituloPrioridad = Integer.parseInt(capituloPrioridadField.getValue());
        } catch (NumberFormatException e) {
            Notification.show("La prioridad debe ser un número entero");
            return;
        }

        // Obtener el cuestionario seleccionado en el ComboBox
        Cuestionario seleccionadoCuestionario = cuestionarioComboBox.getValue();

        // Validar que los campos requeridos no estén vacíos
        if (capituloTitulo.isEmpty() || capituloContenido.isEmpty() || seleccionadoCuestionario == null) {
            Notification.show("El título, contenido del capítulo y cuestionario no pueden estar vacíos");
            return;
        }

        // Crear un nuevo objeto Capitulo con los valores ingresados
        Capitulo nuevoCapitulo = new Capitulo(capituloTitulo, capituloContenido, capituloPrioridad, seleccionadoCuestionario);

        // Agregar el nuevo capítulo a la PriorityQueue de capítulos del curso actual
        cursoActual.getCapitulos().add(nuevoCapitulo);

        // Guardar el curso actualizado en la base de datos
        cursosService.saveCursos(cursoActual);

        // Obtener el profesor actual de la sesión y actualizar sus datos
        Profesor currentUser = VaadinSession.getCurrent().getAttribute(Profesor.class);
        if (currentUser != null) {
            for (Cursos curso : currentUser.getCursosCreados()) {
                if (curso.getId().equals(cursoActual.getId())) {
                    curso.setCapitulos(cursoActual.getCapitulos());
                }
            }
            profesorService.saveProfesor(currentUser);
        }

        // Mostrar una notificación de éxito
        Notification.show("Capítulo y cuestionario asociados guardados exitosamente");

        // Limpiar los campos de entrada
        capituloTituloField.clear();
        capituloContenidoField.clear();
        capituloPrioridadField.clear();
        cuestionarioComboBox.clear();

        // Actualizar el acordeón con la nueva PriorityQueue de capítulos
        actualizarAccordion(new PriorityQueue<>(cursoActual.getCapitulos()));
    }

    private void actualizarCapitulo() {
        // Obtener los valores de los campos de entrada
        String capituloTitulo = capituloTituloField.getValue();
        String capituloContenido = capituloContenidoField.getValue();
        int capituloPrioridad;

        try {
            capituloPrioridad = Integer.parseInt(capituloPrioridadField.getValue());
        } catch (NumberFormatException e) {
            Notification.show("La prioridad debe ser un número entero");
            return;
        }

        // Obtener el cuestionario seleccionado en el ComboBox
        Cuestionario seleccionadoCuestionario = cuestionarioComboBox.getValue();

        // Validar que los campos requeridos no estén vacíos
        if (capituloTitulo.isEmpty() || capituloContenido.isEmpty() || seleccionadoCuestionario == null) {
            Notification.show("El título, contenido del capítulo y cuestionario no pueden estar vacíos");
            return;
        }

        // Actualizar los atributos del capítulo actual
        capituloActual.setTitulo(capituloTitulo);
        capituloActual.setDescripcion(capituloContenido);
        capituloActual.setPrioridad(capituloPrioridad);
        capituloActual.setCuestionario(seleccionadoCuestionario);

        // Guardar el curso actualizado en la base de datos
        cursosService.saveCursos(cursoActual);

        // Obtener el profesor actual de la sesión y actualizar sus datos
        Profesor currentUser = VaadinSession.getCurrent().getAttribute(Profesor.class);
        if (currentUser != null) {
            for (Cursos curso : currentUser.getCursosCreados()) {
                if (curso.getId().equals(cursoActual.getId())) {
                    curso.setCapitulos(cursoActual.getCapitulos());
                }
            }
            profesorService.saveProfesor(currentUser);
        }

        // Mostrar una notificación de éxito
        Notification.show("Capítulo actualizado exitosamente");

        // Limpiar los campos de entrada
        capituloTituloField.clear();
        capituloContenidoField.clear();
        capituloPrioridadField.clear();
        cuestionarioComboBox.clear();

        // Mostrar el botón de guardar y ocultar el botón de actualizar
        saveButton.setVisible(true);
        updateButton.setVisible(false);

        // Actualizar el acordeón con la nueva PriorityQueue de capítulos
        actualizarAccordion(new PriorityQueue<>(cursoActual.getCapitulos()));
    }

    private void actualizarAccordion(PriorityQueue<Capitulo> capitulos) {
        // Remover todos los paneles del acordeón
        capituloAccordion.getChildren()
                .filter(component -> component instanceof AccordionPanel)
                .forEach(component -> capituloAccordion.remove((AccordionPanel) component));

        // Añadir los capítulos al acordeón
        capitulos.forEach(capitulo -> {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> editarCapitulo(capitulo));

            VerticalLayout content = new VerticalLayout();
            content.add(new TextField("Título", capitulo.getTitulo()));
            TextArea descripcion = new TextArea("Descripción");
            descripcion.setValue(capitulo.getDescripcion());
            descripcion.setWidthFull();
            content.add(descripcion, editButton);
            capituloAccordion.add(capitulo.getTitulo(), content);
        });
    }

    private void editarCapitulo(Capitulo capitulo) {
        capituloActual = capitulo;
        capituloTituloField.setValue(capitulo.getTitulo());
        capituloContenidoField.setValue(capitulo.getDescripcion());
        capituloPrioridadField.setValue(String.valueOf(capitulo.getPrioridad()));
        cuestionarioComboBox.setValue(capitulo.getCuestionario());
        saveButton.setVisible(false);
        updateButton.setVisible(true);
    }
}
//Final version

