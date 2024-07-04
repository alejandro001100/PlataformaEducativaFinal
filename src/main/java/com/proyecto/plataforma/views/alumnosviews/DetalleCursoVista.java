package com.proyecto.plataforma.views.alumnosviews;

import com.proyecto.plataforma.capaNegocio.Capitulo;
import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.conexionMongoDB.services.CursosService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route(value = "detalle-curso", layout = MainLayout.class)
@PageTitle("Detalle del Curso | Plataforma Educativa")
public class DetalleCursoVista extends VerticalLayout implements HasUrlParameter<String> {

    private final CursosService cursosService;
    private Cursos curso;
    private ComboBox<Capitulo> capituloComboBox;

    @Autowired
    public DetalleCursoVista(CursosService cursosService) {
        this.cursosService = cursosService;
        addClassName("list-view");
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        if (parameter != null && !parameter.isEmpty()) {
            Optional<Cursos> optionalCurso = cursosService.encontrarPorId(parameter);
            if (optionalCurso.isPresent()) {
                this.curso = optionalCurso.get();
                mostrarDetalles();
            } else {
                add(new H2("Curso no encontrado"));
            }
        } else {
            add(new H2("Curso no encontrado"));
        }
    }

    private void mostrarDetalles() {
        if (curso != null) {
            add(new H2("Bienvenido al curso: " + curso.getTitulo()));

            // Índice de capítulos
            VerticalLayout indiceLayout = new VerticalLayout();
            indiceLayout.add(new H2("Índice de Capítulos"));

            capituloComboBox = new ComboBox<>("Seleccionar Capítulo");
            capituloComboBox.setItems(curso.getCapitulos());
            capituloComboBox.setItemLabelGenerator(Capitulo::getTitulo);
            capituloComboBox.addValueChangeListener(event -> {
                Capitulo selectedCapitulo = event.getValue();
                if (selectedCapitulo != null) {
                    mostrarCapitulo(selectedCapitulo);
                }
            });

            indiceLayout.add(capituloComboBox);

            // Mostrar detalles del primer capítulo por defecto
            if (!curso.getCapitulos().isEmpty()) {
                mostrarCapitulo(curso.getCapitulos().peek());
                capituloComboBox.setValue(curso.getCapitulos().peek());
            }

            HorizontalLayout layout = new HorizontalLayout();
            layout.add(indiceLayout);
            layout.setSizeFull();
            add(layout);
        }
    }

    private void mostrarCapitulo(Capitulo capitulo) {
        VerticalLayout capituloLayout = new VerticalLayout();
        capituloLayout.add(new H2(capitulo.getTitulo()), new Paragraph(capitulo.getDescripcion()));

        Button cuestionarioButton = new Button("Realizar Cuestionario");
        cuestionarioButton.addClickListener(e -> {
            System.out.println("Navigating to CuestionarioVista with id: " + capitulo.getCuestionario().getId()); // Debug
            getUI().ifPresent(ui -> ui.navigate(CuestionarioVista.class, capitulo.getCuestionario().getId()));
        });

        capituloLayout.add(cuestionarioButton);

        // Limpiar y agregar el nuevo contenido del capítulo
        removeAll();
        add(new H2("Bienvenido al curso: " + curso.getTitulo()), capituloLayout);
    }
}
//Final version

