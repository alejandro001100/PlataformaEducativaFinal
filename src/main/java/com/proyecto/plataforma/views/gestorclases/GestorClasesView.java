package com.proyecto.plataforma.views.gestorclases;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.CursosLista; // Suponiendo que esta es tu clase de servicio para gestionar cursos
import com.proyecto.plataforma.views.MainLayout;
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
