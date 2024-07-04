package com.proyecto.plataforma.views.adminViews.gestionusuario;

import com.proyecto.plataforma.capaNegocio.Admin;
import com.proyecto.plataforma.capaNegocio.Estudiante;
import com.proyecto.plataforma.capaNegocio.Profesor;
import com.proyecto.plataforma.capaNegocio.User;
import com.proyecto.plataforma.conexionMongoDB.services.AdminService;
import com.proyecto.plataforma.conexionMongoDB.services.EstudianteService;
import com.proyecto.plataforma.conexionMongoDB.services.ProfesorService;
import com.proyecto.plataforma.conexionMongoDB.services.UserService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Route(value = "gestion-usuario", layout = MainLayout.class)
public class GestionUsuarioView extends VerticalLayout {

    private final AdminService adminService;
    private final EstudianteService estudianteService;
    private final ProfesorService profesorService;
    private final UserService userService;
    private final Grid<User> grid;
    private ListDataProvider<User> dataProvider;

    public GestionUsuarioView(AdminService adminService, EstudianteService estudianteService, ProfesorService profesorService, UserService userService) {
        this.adminService = adminService;
        this.estudianteService = estudianteService;
        this.profesorService = profesorService;
        this.userService = userService;

        setWidth("100%");
        getStyle().set("flex-grow", "1");

        HorizontalLayout layoutRow = new HorizontalLayout();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");

        H3 h3 = new H3("Gestión de usuarios");
        h3.setWidth("max-content");

        layoutRow.add(h3);
        layoutRow.setFlexGrow(1, h3);
        layoutRow.setVerticalComponentAlignment(Alignment.END, h3);

        grid = new Grid<>(User.class);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setWidth("100%");
        grid.getStyle().set("flex-grow", "0");

        List<User> users = new ArrayList<>();
        users.addAll(StreamSupport.stream(adminService.findAll().spliterator(), false).collect(Collectors.toList()));
        users.addAll(StreamSupport.stream(estudianteService.findAll().spliterator(), false).collect(Collectors.toList()));
        users.addAll(StreamSupport.stream(profesorService.findAll().spliterator(), false).collect(Collectors.toList()));

        dataProvider = DataProvider.ofCollection(users);
        grid.setDataProvider(dataProvider);

        grid.setColumns("nombre", "apellido", "correo", "rol");

        grid.addColumn(new ComponentRenderer<>(user -> {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> abrirFormulario(user));
            return editButton;
        })).setHeader("Acciones");

        grid.addColumn(new ComponentRenderer<>(user -> {
            Button deleteButton = new Button("Eliminar");
            deleteButton.addClickListener(event -> {
                if (user instanceof Admin) {
                    adminService.delete((Admin) user);
                } else if (user instanceof Estudiante) {
                    estudianteService.delete((Estudiante) user);
                } else if (user instanceof Profesor) {
                    profesorService.delete((Profesor) user);
                }
                dataProvider.getItems().remove(user);
                dataProvider.refreshAll();
            });
            return deleteButton;
        })).setHeader("Acciones");

        Button buttonPrimary = new Button("Añadir Usuario");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(event -> abrirFormulario(new User()));

        Button deleteSelectedButton = new Button("Eliminar Seleccionados");
        deleteSelectedButton.setWidth("min-content");
        deleteSelectedButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteSelectedButton.addClickListener(event -> {
            List<User> selectedUsers = grid.getSelectedItems().stream().collect(Collectors.toList());
            selectedUsers.forEach(user -> {
                if (user instanceof Admin) {
                    adminService.delete((Admin) user);
                } else if (user instanceof Estudiante) {
                    estudianteService.delete((Estudiante) user);
                } else if (user instanceof Profesor) {
                    profesorService.delete((Profesor) user);
                }
                dataProvider.getItems().remove(user);
            });
            dataProvider.refreshAll();
        });

        add(layoutRow, grid, buttonPrimary, deleteSelectedButton);
    }

    private void abrirFormulario(User user) {
        Dialog dialog = new Dialog();
        FormLayout formLayout = new FormLayout();

        TextField nombreField = new TextField("Nombre");
        nombreField.setValue(user.getNombre() != null ? user.getNombre() : "");
        TextField apellidoField = new TextField("Apellido");
        apellidoField.setValue(user.getApellido() != null ? user.getApellido() : "");
        TextField correoField = new TextField("Correo");
        correoField.setValue(user.getCorreo() != null ? user.getCorreo() : "");
        PasswordField passwordField = new PasswordField("Contraseña");
        ComboBox<String> rolField = new ComboBox<>("Rol");
        rolField.setItems("Estudiante", "Profesor", "Admin");
        rolField.setValue(user.getRol() != null ? user.getRol() : "");

        formLayout.add(nombreField, apellidoField, correoField, passwordField, rolField);

        Button saveButton = new Button("Guardar", event -> {
            user.setNombre(nombreField.getValue());
            user.setApellido(apellidoField.getValue());
            user.setCorreo(correoField.getValue());
            user.setContraseña(passwordField.getValue());
            user.setRol(rolField.getValue());

            if (user instanceof Admin) {
                adminService.saveAdmin((Admin) user);
            } else if (user instanceof Estudiante) {
                estudianteService.saveEstudiante((Estudiante) user);
            } else if (user instanceof Profesor) {
                profesorService.saveProfesor((Profesor) user);
            } else {
                if ("Admin".equals(user.getRol())) {
                    adminService.saveAdmin(new Admin(user.getNombre(), user.getApellido(), user.getCorreo(), user.getContraseña(), user.getRol(), UUID.randomUUID().toString()));
                } else if ("Estudiante".equals(user.getRol())) {
                    estudianteService.saveEstudiante(new Estudiante(user.getNombre(), user.getApellido(), user.getCorreo(), user.getContraseña(), user.getRol(), UUID.randomUUID().toString()));
                } else if ("Profesor".equals(user.getRol())) {
                    profesorService.saveProfesor(new Profesor(user.getNombre(), user.getApellido(), user.getCorreo(), user.getContraseña(), user.getRol(), UUID.randomUUID().toString()));
                }
            }

            apellidoField.setErrorMessage("Por favor, ingrese un apellido válido.");
            apellidoField.setPattern("[a-zA-Z]+");
            nombreField.setErrorMessage("Por favor, ingrese un apellido válido.");
            nombreField.setPattern("[a-zA-Z]+");
            correoField.setErrorMessage("Por favor, ingrese un correo válido.");
            correoField.setPattern("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");

            dataProvider.getItems().clear();
            dataProvider.getItems().addAll(StreamSupport.stream(adminService.findAll().spliterator(), false).collect(Collectors.toList()));
            dataProvider.getItems().addAll(StreamSupport.stream(estudianteService.findAll().spliterator(), false).collect(Collectors.toList()));
            dataProvider.getItems().addAll(StreamSupport.stream(profesorService.findAll().spliterator(), false).collect(Collectors.toList()));
            dataProvider.refreshAll();
            dialog.close();
        });

        Button cancelButton = new Button("Cancelar", event -> dialog.close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, buttonsLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }
}
//Final version

