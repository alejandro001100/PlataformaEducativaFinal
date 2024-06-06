package com.proyecto.plataforma.views.login;

import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.data.Profesor;
import com.proyecto.plataforma.data.User;
import com.proyecto.plataforma.services.EstudianteService;
import com.proyecto.plataforma.services.ProfesorService;
import com.proyecto.plataforma.services.UserService;
import com.proyecto.plataforma.views.gestionusuario.GestionUsuarioView;
import com.proyecto.plataforma.views.registro.RegistroView;
import com.proyecto.plataforma.views.restablecerpassword.RestablecerPasswordView;
import com.proyecto.plataforma.views.creadorcurso.CreadorCursoView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "")
@PageTitle("Login | Proyecto Plataforma Aprendizaje")
public class LoginView extends VerticalLayout {

    private final EstudianteService estudianteService;
    private final ProfesorService profesorService;

    public LoginView(EstudianteService estudianteService, ProfesorService profesorService) {
        this.estudianteService = estudianteService;
        this.profesorService = profesorService;

        setWidth("100%");
        getStyle().set("flex-grow", "1");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        H3 title = new H3("¡Le damos la bienvenida a Plataforma de Aprendizaje UDLA!");
        H5 subtitle = new H5("Inicie sesión en su cuenta");

        EmailField emailField = new EmailField("Correo electrónico");
        emailField.setWidthFull();

        PasswordField passwordField = new PasswordField("Contraseña");
        passwordField.setWidthFull();

        RouterLink forgotPasswordLink = new RouterLink("¿Olvidó su contraseña?", RestablecerPasswordView.class);
        forgotPasswordLink.getElement().getStyle().set("margin-top", "10px");

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setWidth("100%");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        loginButton.getStyle().set("margin-top", "10px");
        loginButton.addClickListener(event -> {
            String correo = emailField.getValue();
            String contraseña = passwordField.getValue();
            Profesor profesor = profesorService.findByCorreo(correo);
            Estudiante estudiante= estudianteService.findByCorreo(correo);
            if (estudiante!= null && estudiante.getContraseña().equals(contraseña)) {
                VaadinSession.getCurrent().setAttribute(Estudiante.class, estudiante);
                Notification.show("Inicio de sesión exitoso!", 3000, Notification.Position.MIDDLE);

                getUI().ifPresent(ui -> ui.navigate(CreadorCursoView.class));

                // Redirigir según el rol del usuario
            } else if (profesor!= null && profesor.getContraseña().equals(contraseña)) {
                VaadinSession.getCurrent().setAttribute(Profesor.class, profesor);
                Notification.show("Inicio de sesión exitoso!", 3000, Notification.Position.MIDDLE);
                //getUI().ifPresent(ui -> ui.navigate(CreadorCursoView.class));
                getUI().ifPresent(ui -> ui.navigate(GestionUsuarioView.class));

            } else {
                Notification.show("Correo o contraseña incorrectos.", 3000, Notification.Position.MIDDLE);
            }
        });

        HorizontalLayout actions = new HorizontalLayout(loginButton);
        actions.setWidthFull();
        actions.setJustifyContentMode(JustifyContentMode.CENTER);
        actions.setAlignItems(Alignment.CENTER);

        Paragraph noAccountText = new Paragraph("¿No tiene una cuenta?");
        RouterLink registerLink = new RouterLink("Regístrese", RegistroView.class);
        registerLink.getElement().getStyle().set("margin-left", "0.5em");

        HorizontalLayout registerLayout = new HorizontalLayout(noAccountText, registerLink);
        registerLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        registerLayout.setAlignItems(Alignment.CENTER);

        add(title, subtitle, emailField, passwordField, forgotPasswordLink, actions, registerLayout);
    }
}
