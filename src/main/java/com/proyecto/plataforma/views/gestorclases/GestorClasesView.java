package com.proyecto.plataforma.views.gestorclases;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.CursosLista;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Route(value = "gestor-clases", layout = MainLayout.class)
@UIScope
@Component
public class GestorClasesView extends VerticalLayout {

    private final CursosLista cursosLista; // Servicio para manejar cursos
    private final Grid<Cursos> grid = new Grid<>(Cursos.class);

    @Autowired
    public GestorClasesView(CursosLista cursosLista) {
        this.cursosLista = cursosLista;
        Collections.sort(cursosLista.getCursosLista());
        setSizeFull();
        configureGrid();
        add(grid);
        updateGrid();
    }

    private void configureGrid() {
        grid.setColumns("titulo", "descripcion", "area"); // Asegúrate de usar las propiedades correctas de la clase Cursos
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
    }

    private void updateGrid() {
        // Se ordenan los cursos en orden alfabético de acuerdo al título
        grid.setItems(cursosLista.getCursosLista());
    }
}
/*

package com.proyecto.plataforma.views.gestorclases;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.CursosLista; // Suponiendo que esta es tu clase de servicio para gestionar cursos
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
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
    // Servicio para manejar cursos
    private final Grid<Cursos> grid = new Grid<>(Cursos.class);




    @Autowired
    public GestorClasesView(CursosLista cursosLista) {
        this.cursosLista = cursosLista;

        Collections.sort(cursosLista.getCursosLista());

        setSizeFull();
        configureGrid();
        add(grid);
        grid.setItems(Collections.emptyList());
        updateGrid();

    }


    private void configureGrid() {
        grid.setColumns("titulo", "descripcion", "area"); // Asegúrate de usar las propiedades correctas de la clase Cursos
        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        grid.addComponentColumn(curso -> {
            Button button = new Button("Ver detalles");
            button.addClickListener(event -> openDialog(curso));
            return button;
        }).setHeader("Acción");
    }

    private void updateGrid() {
        // Se ordenan los cursos en orden alfabético de acuerdo al título
        grid.setItems(cursosLista.getCursosLista ());
    }

    private void openDialog(Cursos curso) {
              Dialog dialog = new Dialog();
        FormLayout formLayout = new FormLayout();

        dialog.add("Detalles del curso: \n" + curso.getTitulo());
        dialog.add("Descripción: \n" + curso.getDescripcion());
        dialog.add("Área: \n" + curso.getArea());

 Button cerrarButton = new Button("Cerrar", event -> dialog.close());
        dialog.add(cerrarButton);
        Button irButton = new Button("Ir al curso", event -> dialog.close());
        dialog.add(irButton);

        dialog.open();

        H3 titulo = new H3("Detalles del curso: " + curso.getTitulo());

        // Detalles del curso
        Paragraph descripcion = new Paragraph("Descripción: " + curso.getDescripcion());
        Paragraph area = new Paragraph("Área: " + curso.getArea());

        // Botones
        Button cerrarButton = new Button("Cerrar", event -> dialog.close());
        Button irButton = new Button("Ir al curso", event -> {
            // Aquí puedes añadir la lógica para navegar a la página del curso
            dialog.close();
        });

        // Layouts para los botones y contenido
        HorizontalLayout buttonLayout = new HorizontalLayout(cerrarButton, irButton);
        buttonLayout.setJustifyContentMode(JustifyContentMode.END);

        VerticalLayout contentLayout = new VerticalLayout(titulo, descripcion, area, buttonLayout);
        contentLayout.setSpacing(true);
        contentLayout.setPadding(true);
        contentLayout.setWidth("400px"); // Ajusta el ancho según tus necesidades

        dialog.add(contentLayout);
        dialog.open();
    }
}
*/