package com.proyecto.plataforma.views.gestionusuario;

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

    private final UserService userService;
    private final Grid<User> multiSelectGrid;
    private ListDataProvider<User> dataProvider;

    public GestionUsuarioView(UserService userService) {
        this.userService = userService;
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

        multiSelectGrid = new Grid<>(User.class);
        multiSelectGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        multiSelectGrid.setWidth("100%");
        multiSelectGrid.getStyle().set("flex-grow", "0");

        List<User> users = StreamSupport.stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        dataProvider = DataProvider.ofCollection(users);
        multiSelectGrid.setDataProvider(dataProvider);

        multiSelectGrid.removeColumnByKey("id");
        multiSelectGrid.setColumns("nombre", "apellido", "correo", "rol");

        multiSelectGrid.addColumn(new ComponentRenderer<>(user -> {
            Button editButton = new Button("Editar");
            editButton.addClickListener(event -> abrirFormularioUsuario(user));
            return editButton;
        })).setHeader("Acciones");

        multiSelectGrid.addColumn(new ComponentRenderer<>(user -> {
            Button deleteButton = new Button("Eliminar");
            deleteButton.addClickListener(event -> {
                userService.delete(user);
                dataProvider.getItems().remove(user);
                dataProvider.refreshAll();
            });
            return deleteButton;
        })).setHeader("Acciones");

        Button buttonPrimary = new Button("Añadir Usuario");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(event -> abrirFormularioUsuario(new User()));

        Button deleteSelectedButton = new Button("Eliminar Seleccionados");
        deleteSelectedButton.setWidth("min-content");
        deleteSelectedButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteSelectedButton.addClickListener(event -> {
            List<User> selectedUsers = multiSelectGrid.getSelectedItems().stream().collect(Collectors.toList());
            selectedUsers.forEach(user -> {
                userService.delete(user);
                dataProvider.getItems().remove(user);
            });
            dataProvider.refreshAll();
        });

        add(layoutRow, multiSelectGrid, buttonPrimary, deleteSelectedButton);
    }

    private void abrirFormularioUsuario(User user) {
        Dialog dialog = new Dialog();
        FormLayout formLayout = new FormLayout();

        TextField nombreField = new TextField("Nombre");
        nombreField.setValue(user.getNombre() != null ? user.getNombre() : "");
        TextField apellidoField = new TextField("Apellido");
        apellidoField.setValue(user.getApellido() != null ? user.getApellido() : "");
        TextField correoField = new TextField("Correo");
        correoField.setValue(user.getCorreo() != null ? user.getCorreo() : "");

        ComboBox<String> rolField = new ComboBox<>("Rol");
        rolField.setItems("Estudiante", "Profesor","Admin");
        rolField.setValue(user.getRol() != null ? user.getRol() : "");

        formLayout.add(nombreField, apellidoField, correoField, rolField);

        Button saveButton = new Button("Guardar", event -> {
            user.setNombre(nombreField.getValue());
            user.setApellido(apellidoField.getValue());
            user.setCorreo(correoField.getValue());
            user.setRol(rolField.getValue());

            userService.save(user);
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
