package com.proyecto.plataforma.views.creadorcapituloscurso;

import com.proyecto.plataforma.data.Capitulo;
import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.services.CursosService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route(value = "creador-capitulos", layout = MainLayout.class)
public class CreadorCapitulosView extends VerticalLayout {

    @Autowired
    public CreadorCapitulosView(CursosService cursosService) {
        String cursoId = (String) VaadinSession.getCurrent().getAttribute("cursoId");
        Optional<Cursos> optionalCurso = cursosService.findById(cursoId);

        if (!optionalCurso.isPresent()) {
            Notification.show("No se encontró el curso seleccionado");
            return;
        }

        Cursos curso = optionalCurso.get();

        TextField capituloTituloField = new TextField("Título del Capítulo");
        TextField capituloContenidoField = new TextField("Contenido del Capítulo");

        Button saveButton = new Button("Guardar Capítulo", event -> {
            String capituloTitulo = capituloTituloField.getValue();
            String capituloContenido = capituloContenidoField.getValue();

            if (capituloTitulo.isEmpty() || capituloContenido.isEmpty()) {
                Notification.show("El título y contenido del capítulo no pueden estar vacíos");
                return;
            }

            curso.getCapitulos().add(new Capitulo(capituloTitulo, capituloContenido));
            cursosService.saveCursos(curso);
            Notification.show("Capítulo guardado exitosamente");
            capituloTituloField.clear();
            capituloContenidoField.clear();
        });

        Button regresarButton = new Button("Regresar", event -> getUI().ifPresent(ui -> ui.navigate(MainLayout.class)));

        add(capituloTituloField, capituloContenidoField, new HorizontalLayout(saveButton, regresarButton));
    }
}
