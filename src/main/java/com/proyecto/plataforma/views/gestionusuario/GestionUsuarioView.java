package com.proyecto.plataforma.views.gestionusuario;

import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.data.Profesor;
import com.proyecto.plataforma.services.EstudianteService;
import com.proyecto.plataforma.services.ProfesorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.proyecto.plataforma.data.User;
import com.proyecto.plataforma.services.UserService;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Route("gestion-usuario")
public class GestionUsuarioView extends VerticalLayout {


    private final EstudianteService estudianteService;
    private final ProfesorService profesorService;
    private final Grid<Profesor> gridProfesor;
    private final Grid<Estudiante> gridEstudiante;
    private ListDataProvider<Profesor> dataProviderP;
    private ListDataProvider<Estudiante> dataProviderE;

    public GestionUsuarioView(EstudianteService estudianteService, ProfesorService profesorService) {
        this.estudianteService = estudianteService;
        this.profesorService= profesorService;


        setWidth("100%");
        getStyle().set("flex-grow", "1");

        HorizontalLayout layoutRow = new HorizontalLayout();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");

        H3 h3 = new H3("Gestión de usuarios");
        h3.setWidth("max-content");

        Button logoutButton = new Button("Cerrar sesión");
        logoutButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        logoutButton.addClickListener(event -> {
            VaadinSession.getCurrent().getSession().invalidate();
            VaadinSession.getCurrent().close();
            getUI().ifPresent(ui -> ui.navigate("login"));
        });

        layoutRow.add(h3, logoutButton);
        layoutRow.setFlexGrow(1, h3);
        layoutRow.setFlexGrow(0, logoutButton);
        layoutRow.setVerticalComponentAlignment(Alignment.END, logoutButton);

        gridProfesor = new Grid<>(Profesor.class);
        gridProfesor.setSelectionMode(Grid.SelectionMode.MULTI);
        gridProfesor.setWidth("100%");
        gridProfesor.getStyle().set("flex-grow", "0");

        gridEstudiante = new Grid<>(Estudiante.class);
        gridEstudiante.setSelectionMode(Grid.SelectionMode.MULTI);
        gridEstudiante.setWidth("100%");
        gridEstudiante.getStyle().set("flex-grow", "0");

        List<Profesor> profesor = StreamSupport.stream(profesorService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        dataProviderP = DataProvider.ofCollection(profesor);
        gridProfesor.setDataProvider(dataProviderP);

        List<Estudiante> estudiante = StreamSupport.stream(estudianteService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        dataProviderE = DataProvider.ofCollection(estudiante);
        gridEstudiante.setDataProvider(dataProviderE);

        gridProfesor.removeColumnByKey("id");
        gridProfesor.setColumns("nombre", "apellido", "correo", "rol");

        gridEstudiante.removeColumnByKey("id");
        gridEstudiante.setColumns("nombre", "apellido", "correo", "rol");

        gridProfesor.addColumn(new ComponentRenderer<>(profesor1 -> {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> abrirFormularioProfesor(profesor1));
            return editButton;
        })).setHeader("Acciones");

        gridEstudiante.addColumn(new ComponentRenderer<>(estudiante1 -> {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> abrirFormularioEstudiante(estudiante1));
            return editButton;
        })).setHeader("Acciones");

        gridProfesor.addColumn(new ComponentRenderer<>(profesor1 -> {
            Button deleteButton = new Button("Eliminar");
            deleteButton.addClickListener(event -> {
                profesorService.delete(profesor1);
                dataProviderP.getItems().remove(profesor1);
                dataProviderP.refreshAll();
            });
            return deleteButton;
        })).setHeader("Acciones");

        gridEstudiante.addColumn(new ComponentRenderer<>(estudiante1 -> {
            Button deleteButton = new Button("Eliminar");
            deleteButton.addClickListener(event -> {
                estudianteService.delete(estudiante1);
                dataProviderE.getItems().remove(estudiante1);
                dataProviderE.refreshAll();
            });
            return deleteButton;
        })).setHeader("Acciones");

        Button buttonPrimary = new Button("Añadir Usuario");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(event -> abrirFormularioProfesor(new Profesor()));
        buttonPrimary.addClickListener(event -> abrirFormularioEstudiante(new Estudiante()));

        Button deleteSelectedButton = new Button("Eliminar Seleccionados");
        deleteSelectedButton.setWidth("min-content");
        deleteSelectedButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteSelectedButton.addClickListener(event -> {
            List<Profesor> selectedProfesores = gridProfesor.getSelectedItems().stream().collect(Collectors.toList());
            selectedProfesores.forEach(profesor1 -> {
                profesorService.delete(profesor1);
                dataProviderP.getItems().remove(profesor1);
            });
            dataProviderP.refreshAll();

            List<Estudiante> selectedEstudiantes = gridEstudiante.getSelectedItems().stream().collect(Collectors.toList());
            selectedEstudiantes.forEach(estudiante1 -> {
                estudianteService.delete(estudiante1);
                dataProviderE.getItems().remove(estudiante1);
            });
            dataProviderE.refreshAll();

        });

        add(layoutRow, gridProfesor, buttonPrimary, deleteSelectedButton);
        add(layoutRow, gridEstudiante, buttonPrimary, deleteSelectedButton);
    }

    private void abrirFormularioProfesor(Profesor profesor) {
        Dialog dialog = new Dialog();
        FormLayout formLayout = new FormLayout();

        TextField nombreField = new TextField("Nombre");
        nombreField.setValue(profesor.getNombre() != null ? profesor.getNombre() : "");
        nombreField.setValue(profesor.getNombre() != null ? profesor.getNombre() : "");
        TextField apellidoField = new TextField("Apellido");
        apellidoField.setValue(profesor.getApellido() != null ? profesor.getApellido() : "");
        TextField correoField = new TextField("Correo");
        correoField.setValue(profesor.getCorreo() != null ? profesor.getCorreo() : "");

        ComboBox<String> rolField = new ComboBox<>("Rol");
        rolField.setItems("Estudiante", "Profesor","Admin");
        rolField.setValue(profesor.getRol() != null ? profesor.getRol() : "");

        formLayout.add(nombreField, apellidoField, correoField, rolField);

        Button saveButton = new Button("Guardar", event -> {
            profesor.setNombre(nombreField.getValue());
            profesor.setApellido(apellidoField.getValue());
            profesor.setCorreo(correoField.getValue());
            profesor.setRol(rolField.getValue());

            profesorService.saveProfesor(profesor);
            dataProviderP.refreshAll();
            dialog.close();
        });

        Button cancelButton = new Button("Cancelar", event -> dialog.close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, buttonsLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }
    private void abrirFormularioEstudiante(Estudiante estudiante) {
        Dialog dialog = new Dialog();
        FormLayout formLayout = new FormLayout();

        TextField nombreField = new TextField("Nombre");
        nombreField.setValue(estudiante.getNombre() != null ? estudiante  .getNombre() : "");
        nombreField.setValue(estudiante.getNombre() != null ? estudiante.getNombre() : "");
        TextField apellidoField = new TextField("Apellido");
        apellidoField.setValue(estudiante.getApellido() != null ? estudiante.getApellido() : "");
        TextField correoField = new TextField("Correo");
        correoField.setValue(estudiante.getCorreo() != null ? estudiante.getCorreo() : "");

        ComboBox<String> rolField = new ComboBox<>("Rol");
        rolField.setItems("Estudiante", "Profesor","Admin");
        rolField.setValue(estudiante.getRol() != null ? estudiante.getRol() : "");

        formLayout.add(nombreField, apellidoField, correoField, rolField);

        Button saveButton = new Button("Guardar", event -> {
            estudiante.setNombre(nombreField.getValue());
            estudiante.setApellido(apellidoField.getValue());
            estudiante.setCorreo(correoField.getValue());
            estudiante.setRol(rolField.getValue());

            estudianteService.saveEstudiante(estudiante);
            dataProviderP.refreshAll();
            dialog.close();
        });

        Button cancelButton = new Button("Cancelar", event -> dialog.close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, buttonsLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

}

