package com.proyecto.plataforma.views.profesoresViews.gestorclases;

import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.capaNegocio.Estudiante;
import com.proyecto.plataforma.capaNegocio.NotaCuestionario;
import com.proyecto.plataforma.capaNegocio.Profesor;
import com.proyecto.plataforma.estructuras.*;
import com.proyecto.plataforma.views.MainLayout;
import com.proyecto.plataforma.views.profesoresViews.creadorcapituloscurso.CreadorCapitulosView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Route(value = "gestor-clases", layout = MainLayout.class)
@UIScope
@Component
public class GestorClasesView extends VerticalLayout {

    private final CursosLista cursosLista;
    private final Grid<Cursos> grid = new Grid<>(Cursos.class);

    @Autowired
    public GestorClasesView(CursosLista cursosLista) {
        this.cursosLista = cursosLista;


        setSizeFull();
        configureGrid();
        add(grid);
        updateGrid();
    }

    private void configureGrid() {
        grid.setColumns("titulo", "descripcion", "area", "tema", "profesor");
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.addComponentColumn(curso -> {
            Button button = new Button("Ver detalles");
            button.addClickListener(event -> openDialog(curso));
            return button;
        }).setHeader("Acción");

        grid.addComponentColumn(curso -> {
            Button button = new Button("Eliminar");
            button.addClickListener(event -> {
                cursosLista.eliminarCursos(curso);
                updateGrid();
            });
            return button;
        }).setHeader("Acción");

        grid.addComponentColumn(curso -> {
            Button button = new Button("Ver Alumnos");
            button.addClickListener(event -> openStudentsView(curso));
            return button;
        }).setHeader("Acción");
    }

    private void updateGrid() {
        Profesor currentUser = VaadinSession.getCurrent().getAttribute(Profesor.class);
        if (currentUser != null) {
            System.out.println("Profesor actual: " + currentUser.getCorreo());
            cursosLista.cargarCursos();
            List<Cursos> cursosDelProfesor = cursosLista.buscarPorProfesor(currentUser.getNombre());
            System.out.println("Cursos del profesor: " + cursosDelProfesor.size());
            grid.setItems(cursosDelProfesor);
        } else {
            System.out.println("No se encontró un profesor en la sesión.");
            grid.setItems(Collections.emptyList());
        }
    }

    private void openDialog(Cursos curso) {
        Dialog dialog = new Dialog();

        TextField tituloField = new TextField("Título");
        tituloField.setValue(curso.getTitulo() != null ? curso.getTitulo() : "");

        TextField descripcionField = new TextField("Descripción");
        descripcionField.setValue(curso.getDescripcion() != null ? curso.getDescripcion() : "");

        ComboBox<String> comboArea = new ComboBox<>("Área");
        comboArea.setItems("Reciclaje desde casa", "Leyes en cuanto al reciclaje", "Impacto ambiental del reciclaje");
        comboArea.setValue(curso.getArea() != null ? curso.getArea() : "");

        TextField temaField = new TextField("Tema");
        temaField.setValue(curso.getTema() != null ? curso.getTema() : "");

        TextField profesorField = new TextField("Profesor");
        profesorField.setValue(curso.getProfesor() != null ? curso.getProfesor() : "");
        profesorField.setReadOnly(true);

        Button cerrarButton = new Button("Cerrar", event -> dialog.close());
        Button editarButton = new Button("Guardar cambios", event -> {
            curso.setTitulo(tituloField.getValue());
            curso.setDescripcion(descripcionField.getValue());
            curso.setArea(comboArea.getValue());
            curso.setTema(temaField.getValue());

            cursosLista.guardarCursos(curso);

            dialog.close();
            updateGrid();
        });

        Button irButton = new Button("Añadir Capítulos", event -> {
            VaadinSession.getCurrent().setAttribute("cursoId", curso.getId());
            getUI().ifPresent(ui -> ui.navigate(CreadorCapitulosView.class));
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(cerrarButton, editarButton, irButton);
        buttonLayout.setJustifyContentMode(JustifyContentMode.END);

        VerticalLayout contentLayout = new VerticalLayout(tituloField, descripcionField, comboArea, temaField, profesorField, buttonLayout);
        contentLayout.setSpacing(true);
        contentLayout.setPadding(true);
        contentLayout.setWidth("400px");

        dialog.add(contentLayout);
        dialog.open();
    }

    private void openStudentsView(Cursos curso) {
        VerticalLayout layout = new VerticalLayout();
        Grid<Estudiante> alumnosGrid = new Grid<>(Estudiante.class);
        alumnosGrid.setColumns("nombre", "apellido", "correo");

        alumnosGrid.addColumn(estudiante -> {
            StringBuilder notas = new StringBuilder();
            for (NotaCuestionario nota : estudiante.getNotasCuestionarios()) {
                notas.append(nota.getTituloCuestionario()).append(": ").append(nota.getNota()).append("\n");
            }
            return notas.toString();
        }).setHeader("Notas de Cuestionarios");

        alumnosGrid.setItems(curso.getAlumnosRegistrados());

        layout.add(new H2("Alumnos registrados en " + curso.getTitulo()), alumnosGrid);
        layout.setSizeFull();

        Dialog alumnosDialog = new Dialog();
        alumnosDialog.add(layout);
        alumnosDialog.setWidth("80%");
        alumnosDialog.setHeight("80%");
        alumnosDialog.open();
    }
}


//Final version

