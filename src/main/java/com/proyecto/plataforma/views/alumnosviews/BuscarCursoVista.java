package com.proyecto.plataforma.views.alumnosviews;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.services.CursosService;
import com.proyecto.plataforma.services.EstudianteService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Route(value = "buscar-curso", layout = MainLayout.class)
@PageTitle("Buscar Curso | Plataforma Educativa")
public class BuscarCursoVista extends VerticalLayout {

    private final CursosService cursosService;
    private final EstudianteService estudianteService;
    private final Grid<Cursos> grid = new Grid<>(Cursos.class);
    private final TextField filtroTema = new TextField("Tema");
    private final TextField filtroProfesor = new TextField("Profesor");

    @Autowired
    public BuscarCursoVista(CursosService cursosService, EstudianteService estudianteService) {
        this.cursosService = cursosService;
        this.estudianteService = estudianteService;

        setSizeFull();
        configurarGrid();
        configurarFiltros();
        add(new HorizontalLayout(filtroTema, filtroProfesor), grid);
        updateGrid();
    }

    private void configurarGrid() {
        grid.setColumns("titulo", "descripcion", "area", "tema", "profesor");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.addComponentColumn(curso -> {
            Button inscribirseButton = new Button("Inscribirse");
            inscribirseButton.addClickListener(event -> inscribirseEnCurso(curso));
            return inscribirseButton;
        }).setHeader("Acción");
    }

    private void configurarFiltros() {
        filtroTema.setPlaceholder("Buscar por tema");
        filtroProfesor.setPlaceholder("Buscar por profesor");

        filtroTema.addValueChangeListener(event -> updateGrid());
        filtroProfesor.addValueChangeListener(event -> updateGrid());
    }

    private void updateGrid() {
        String tema = filtroTema.getValue();
        String profesor = filtroProfesor.getValue();

        List<Cursos> cursosList = StreamSupport
                .stream(cursosService.encontrarTodos().spliterator(), false)
                .filter(curso -> (tema == null || tema.isEmpty() || curso.getTema().contains(tema)) &&
                        (profesor == null || profesor.isEmpty() || curso.getProfesor().contains(profesor)))
                .collect(Collectors.toList());

        grid.setItems(cursosList);
    }

    private void inscribirseEnCurso(Cursos curso) {
        Estudiante estudiante = getCurrentEstudiante();
        if (estudiante != null) {
            if (estudiante.getCursosTomados().contains(curso)) {
                Notification.show("Ya estás inscrito en este curso: " + curso.getTitulo(), 3000, Notification.Position.MIDDLE);
                return;
            }

            curso.agregarAlumnoRegistrado(estudiante);
            cursosService.saveCursos(curso);

            estudiante.agregarCursoTomado(curso);
            estudianteService.saveEstudiante(estudiante);

            Notification.show("Te has inscrito en el curso: " + curso.getTitulo(), 3000, Notification.Position.MIDDLE);
        } else {
            Notification.show("No se encontró el estudiante", 3000, Notification.Position.MIDDLE);
        }
    }

    private Estudiante getCurrentEstudiante() {
        return (Estudiante) VaadinSession.getCurrent().getAttribute(Estudiante.class);
    }
}
//Final version

