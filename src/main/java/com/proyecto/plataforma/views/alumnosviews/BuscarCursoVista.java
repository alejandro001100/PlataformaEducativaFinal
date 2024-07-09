package com.proyecto.plataforma.views.alumnosviews;

import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.capaNegocio.Estudiante;
import com.proyecto.plataforma.conexionMongoDB.services.CursosService;
import com.proyecto.plataforma.conexionMongoDB.services.EstudianteService;
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
    private final TextField filtroAutor = new TextField("Autor");

    @Autowired
    public BuscarCursoVista(CursosService cursosService, EstudianteService estudianteService) {
        this.cursosService = cursosService;
        this.estudianteService = estudianteService;

        setSizeFull();
        configurarGrid();
        configurarFiltros();
        add(new HorizontalLayout(filtroTema, filtroAutor), grid);
        updateGrid();
    }

    private void configurarGrid() {
        grid.setColumns("titulo", "descripcion", "area", "tema", "autor");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.addComponentColumn(curso -> {
            Button inscribirseButton = new Button("Inscribirse");
            inscribirseButton.addClickListener(event -> inscribirseEnCurso(curso));
            return inscribirseButton;
        }).setHeader("Acción");
    }

    private void configurarFiltros() {
        filtroTema.setPlaceholder("Buscar por tema");
        filtroAutor.setPlaceholder("Buscar por autor");

        filtroTema.addValueChangeListener(event -> updateGrid());
        filtroAutor.addValueChangeListener(event -> updateGrid());
    }

    private void updateGrid() {
        String tema = filtroTema.getValue();
        String autor = filtroAutor.getValue();

        List<Cursos> cursosList = StreamSupport
                .stream(cursosService.encontrarTodos().spliterator(), false)
                .filter(curso -> (tema == null || tema.isEmpty() || curso.getTema().contains(tema)) &&
                        (autor == null || autor.isEmpty() || curso.getAutor().contains(autor)))
                .collect(Collectors.toList());

        grid.setItems(cursosList);
    }

    private void inscribirseEnCurso(Cursos curso) {
        Estudiante estudiante = getCurrentEstudiante();
        if (estudiante != null) {
            if (estudiante.isCursoTomado(curso.getTitulo())==true) {
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

