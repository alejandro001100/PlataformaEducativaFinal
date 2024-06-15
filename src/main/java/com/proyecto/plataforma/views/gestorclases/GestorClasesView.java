package com.proyecto.plataforma.views.gestorclases;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.data.CursosLista; // Suponiendo que esta es tu clase de servicio para gestionar cursos
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

import java.util.ArrayList;
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

        // Se ordenan los cursos en orden alfabético de acuerdo al título
        Collections.sort(cursosLista.getCursosLista());

        setSizeFull();
        configureGrid();
        add(grid);
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

        grid.addComponentColumn(curso -> {
            Button button = new Button("Eliminar");
            button.addClickListener(event -> {
                cursosLista.eliminarCursos(curso);
                updateGrid(); // Actualiza la cuadrícula después de eliminar el curso
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

        ComboBox comboArea = new ComboBox<>("Área");
        comboArea.setItems("Reciclaje desde casa", "Leyes en cuanto al reciclaje", "Impacto ambiental del reciclaje");
        comboArea.setValue(curso.getArea() != null ? curso.getArea() : "");

        // Set the initial value of the combo box
       /* SampleItem initialArea = comboArea.getDataProvider().fetch(new com.vaadin.flow.data.provider.Query<>())
                .filter(item -> item.value().equals(curso.getArea()))
                .findFirst()
                .orElse(null);
        comboArea.setValue(initialArea);*/

        // Botones
        Button cerrarButton = new Button("Cerrar", event -> dialog.close());
        Button editarButton = new Button("Guardar cambios", event -> {
            String nuevoTitulo = tituloField.getValue();
            String nuevaDescripcion = descripcionField.getValue();
            String nuevoArea = comboArea.getValue() != null ? (String) comboArea.getValue() : curso.getArea();


            cursosLista.eliminarCursos(curso);
            // Actualizar el curso con los nuevos valores
            curso.setTitulo(nuevoTitulo);
            curso.setDescripcion(nuevaDescripcion);
            curso.setArea(nuevoArea);
            cursosLista.guardarCursos(curso);


            // Cierra la ventana emergente
            dialog.close();

            // Actualiza el grid para reflejar los cambios
            updateGrid();
        });
        Button irButton = new Button("Añadir Capítulos", event -> {
            // Aquí puedes añadir la lógica para añadir capítulos
        });

        // Layouts para los botones y contenido
        HorizontalLayout buttonLayout = new HorizontalLayout(cerrarButton, editarButton, irButton);
        buttonLayout.setJustifyContentMode(JustifyContentMode.END);

        VerticalLayout contentLayout = new VerticalLayout(tituloField, descripcionField, comboArea, buttonLayout);
        contentLayout.setSpacing(true);
        contentLayout.setPadding(true);
        contentLayout.setWidth("400px"); // Ajusta el ancho según tus necesidades

        dialog.add(contentLayout);
        dialog.open();
    }



}
