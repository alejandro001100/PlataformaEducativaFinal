/*package com.proyecto.plataforma.views.creadorcapituloscurso;

import com.proyecto.plataforma.components.QuillEditor;
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

@Route(value = "creador-capitulos", layout = MainLayout.class)
public class CreadorCapitulosView extends VerticalLayout {

    @Autowired
    public CreadorCapitulosView(CursosService cursosService) {
        String cursoId = (String) VaadinSession.getCurrent().getAttribute("cursoId");
        Cursos curso = cursosService.findById(cursoId);

        if (curso == null) {
            Notification.show("No se encontró el curso seleccionado");
            return;
        }

        TextField capituloTituloField = new TextField("Título del Capítulo");
        QuillEditor capituloContenidoField = new QuillEditor();

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
            capituloContenidoField.setValue("");
        });

        Button regresarButton = new Button("Regresar", event -> getUI().ifPresent(ui -> ui.navigate(MainLayout.class)));

        add(capituloTituloField, capituloContenidoField, new HorizontalLayout(saveButton, regresarButton));
    }
}
*/