package com.proyecto.plataforma.views.registro;

import com.proyecto.plataforma.data.*;
import com.proyecto.plataforma.services.EstudianteService;
import com.proyecto.plataforma.services.ProfesorService;
import com.proyecto.plataforma.services.UserService;
import com.proyecto.plataforma.views.MainLayout;
import com.proyecto.plataforma.views.login.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Registro")
@Route(value = "registro", layout = MainLayout.class)
public class RegistroView extends VerticalLayout {
    /*

    public RegistroView(EstudianteService estudianteService, ProfesorService profesorService) {
        setWidth("100%");
        getStyle().set("flex-grow", "1");
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);

        H3 title = new H3("Regístrate");
        H6 subtitle = new H6("Coloca tus datos para comenzar el registro.");
        subtitle.getStyle().set("text-align", "center");

        TextField nombreField = new TextField("Nombre");
        nombreField.setWidthFull();

        TextField apellidoField = new TextField("Apellido");
        apellidoField.setWidthFull();

        EmailField emailField = new EmailField("Correo electrónico");
        emailField.setWidthFull();

        PasswordField passwordField = new PasswordField("Contraseña");
        passwordField.setWidthFull();

        Select<String> roleSelect = new Select<>();
        roleSelect.setItems("Estudiante", "Profesor");
        roleSelect.setLabel("¿Eres?");
        roleSelect.setWidthFull();

        Button registerButton = new Button("Continuar");
        registerButton.setWidthFull();
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.addClickListener(event -> {
            String nombre = nombreField.getValue();
            String apellido = apellidoField.getValue();
            String correo = emailField.getValue();
            String contraseña = passwordField.getValue();
            String rol = roleSelect.getValue();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || rol == null) {
                Notification.show("Por favor, complete todos los campos.", 3000, Notification.Position.MIDDLE);
                return;
            }

            if (!emailField.isInvalid() && !profesorService.isCorreoRegistrado(correo) && rol.equals("Profesor")) {
                Profesor profesor = new Profesor();
                profesor.setNombre(nombre);
                profesor.setApellido(apellido);
                profesor.setCorreo(correo);
                profesor.setContraseña(contraseña);
                profesor.setRol(rol);
                profesorService.saveProfesor(profesor);
                Notification.show("Registro exitoso!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(LoginView.class));

            }else if(!emailField.isInvalid() && !estudianteService.isCorreoRegistrado(correo) && rol.equals("Estudiante")) {
                Estudiante estudiante = new Estudiante();
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setCorreo(correo);
                estudiante.setContraseña(contraseña);
                estudiante.setRol(rol);
                estudianteService.saveEstudiante(estudiante);

                Notification.show("Registro exitoso!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(LoginView.class));
            } else {
                Notification.show("Correo electrónico no válido o ya registrado.", 3000, Notification.Position.MIDDLE);
            }
        });

        emailField.setErrorMessage("Por favor, ingrese un correo válido.");
        emailField.setPattern("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");

        add(title, subtitle, nombreField, apellidoField, emailField, passwordField, roleSelect, registerButton);
    }*/
    public RegistroView(EstudianteLista estudianteLista, ProfesorLista profesorLista) {
        setWidth("100%");
        getStyle().set("flex-grow", "1");
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);

        H3 title = new H3("Regístrate");
        H6 subtitle = new H6("Coloca tus datos para comenzar el registro.");
        subtitle.getStyle().set("text-align", "center");

        TextField nombreField = new TextField("Nombre");
        nombreField.setWidthFull();

        TextField apellidoField = new TextField("Apellido");
        apellidoField.setWidthFull();

        EmailField emailField = new EmailField("Correo electrónico");
        emailField.setWidthFull();

        PasswordField passwordField = new PasswordField("Contraseña");
        passwordField.setWidthFull();

        Select<String> roleSelect = new Select<>();
        roleSelect.setItems("Estudiante", "Profesor");
        roleSelect.setLabel("¿Eres?");
        roleSelect.setWidthFull();

        Button registerButton = new Button("Continuar");
        registerButton.setWidthFull();
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.addClickListener(event -> {
            String nombre = nombreField.getValue();
            String apellido = apellidoField.getValue();
            String correo = emailField.getValue();
            String contraseña = passwordField.getValue();
            String rol = roleSelect.getValue();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || rol == null) {
                Notification.show("Por favor, complete todos los campos.", 3000, Notification.Position.MIDDLE);
                return;
            }


            if (!emailField.isInvalid() && !profesorLista.isCorreoRegistrado(correo) && rol.equals("Profesor")) {
                Profesor profesor = new Profesor();
                profesor.setNombre(nombre);
                profesor.setApellido(apellido);
                profesor.setCorreo(correo);
                profesor.setContraseña(contraseña);
                profesor.setRol(rol);
                profesorLista.agregarProfesor(profesor);
                Notification.show("Registro exitoso!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(LoginView.class));

            } else if (!emailField.isInvalid() && !estudianteLista.isCorreoRegistrado(correo) && rol.equals("Estudiante")) {
                Estudiante estudiante = new Estudiante();
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setCorreo(correo);
                estudiante.setContraseña(contraseña);
                estudiante.setRol(rol);
                estudianteLista.agregarEstudiante(estudiante);

                Notification.show("Registro exitoso!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(LoginView.class));
            } else {
                Notification.show("Correo electrónico no válido o ya registrado.", 3000, Notification.Position.MIDDLE);
            }
        });

        emailField.setErrorMessage("Por favor, ingrese un correo válido.");
        emailField.setPattern("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");

        add(title, subtitle, nombreField, apellidoField, emailField, passwordField, roleSelect, registerButton);
    }
}