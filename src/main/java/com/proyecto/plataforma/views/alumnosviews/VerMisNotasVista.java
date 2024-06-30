package com.proyecto.plataforma.views.alumnosviews;

import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.data.NotaCuestionario;
import com.proyecto.plataforma.data.User;
import com.proyecto.plataforma.services.EstudianteService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "ver-notas", layout = MainLayout.class)
@PageTitle("Mis Notas | Plataforma Educativa")
public class VerMisNotasVista extends VerticalLayout {

    private final EstudianteService estudianteService;
    private Grid<NotaCuestionario> gridNotasCuestionarios;

    @Autowired
    public VerMisNotasVista(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
        addClassName("ver-notas-vista");
        setSizeFull();

        gridNotasCuestionarios = new Grid<>(NotaCuestionario.class);
        gridNotasCuestionarios.addColumn(notaCuestionario -> "Intento " + notaCuestionario.getIntentos()).setHeader("Intento");
        gridNotasCuestionarios.addColumn(NotaCuestionario::getTituloCuestionario).setHeader("Cuestionario");
        gridNotasCuestionarios.addColumn(NotaCuestionario::getNota).setHeader("Nota");
        gridNotasCuestionarios.setSizeFull();

        add(gridNotasCuestionarios);

        loadNotasCuestionarios();
    }

    private void loadNotasCuestionarios() {
        Estudiante estudiante = getCurrentEstudiante();
        if (estudiante != null) {
            gridNotasCuestionarios.setItems(estudiante.getNotasCuestionarios());
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

