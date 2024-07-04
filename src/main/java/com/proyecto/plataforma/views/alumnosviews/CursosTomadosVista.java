package com.proyecto.plataforma.views.alumnosviews;

import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.capaNegocio.Estudiante;
import com.proyecto.plataforma.capaNegocio.User;
import com.proyecto.plataforma.conexionMongoDB.services.EstudianteService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "cursos-tomados", layout = MainLayout.class)
@PageTitle("Cursos Tomados | Plataforma Educativa")
public class CursosTomadosVista extends VerticalLayout {

    private final EstudianteService estudianteService;
    private Grid<Cursos> gridCursosTomados;

    @Autowired
    public CursosTomadosVista(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
        addClassName("cursos-tomados-vista");
        setSizeFull();

        gridCursosTomados = new Grid<>(Cursos.class);
        gridCursosTomados.setColumns("titulo", "descripcion");
        gridCursosTomados.setSizeFull();

        gridCursosTomados.addComponentColumn(curso -> {
            Button volverCursoButton = new Button("Volver al Curso");
            volverCursoButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("detalle-curso/" + curso.getId())));
            return volverCursoButton;
        });

        add(gridCursosTomados);

        loadCursosTomados();
    }

    private void loadCursosTomados() {
        Estudiante estudiante = getCurrentEstudiante();
        if (estudiante != null) {
            gridCursosTomados.setItems(estudiante.getCursosTomados());
        } else {
            Notification.show("No se encontró información del estudiante", 3000, Notification.Position.MIDDLE);
        }
    }

    private Estudiante getCurrentEstudiante() {
        User user = VaadinSession.getCurrent().getAttribute(User.class);
        if (user != null && user instanceof Estudiante) {
            return estudianteService.findByCorreo(user.getCorreo());
        }
        return null;
    }
}
//Final version

