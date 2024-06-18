package com.proyecto.plataforma.views.gestorclases;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.CursosLista;
import com.proyecto.plataforma.views.MainLayout;
import com.proyecto.plataforma.views.creadorcapituloscurso.CreadorCapitulosView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

import java.util.Collections;

@Route(value = "gestor-clases", layout = MainLayout.class)
@UIScope
@Component
public class GestorClasesView extends VerticalLayout {

    private final CursosLista cursosLista;
    private final Grid<Cursos> grid = new Grid<>(Cursos.class);

    @Autowired
    public GestorClasesView(CursosLista cursosLista) {
        this.cursosLista = cursosLista;

        // Ordenar los cursos en orden alfabético de acuerdo al título
        Collections.sort(cursosLista.getCursosLista());

        setSizeFull();
        configureGrid();
        add(grid);
        updateGrid();
    }

    private void configureGrid() {
        grid.setColumns("titulo", "descripcion", "area");
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
    }

    private void updateGrid() {
        grid.setItems(cursosLista.getCursosLista());
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

        Button cerrarButton = new Button("Cerrar", event -> dialog.close());
        Button editarButton = new Button("Guardar cambios", event -> {
            curso.setTitulo(tituloField.getValue());
            curso.setDescripcion(descripcionField.getValue());
            curso.setArea(comboArea.getValue());

            cursosLista.guardarCursos(curso);

            dialog.close();
            updateGrid();
        });
        Button irButton = new Button("Añadir Capítulos", event -> {
            VaadinSession.getCurrent().setAttribute("cursoId", curso.getId());
            getUI().ifPresent(ui -> ui.navigate( CreadorCapitulosView.class));
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(cerrarButton, editarButton, irButton);
        buttonLayout.setJustifyContentMode(JustifyContentMode.END);

        VerticalLayout contentLayout = new VerticalLayout(tituloField, descripcionField, comboArea, buttonLayout);
        contentLayout.setSpacing(true);
        contentLayout.setPadding(true);
        contentLayout.setWidth("400px");

        dialog.add(contentLayout);
        dialog.open();
    }
}
