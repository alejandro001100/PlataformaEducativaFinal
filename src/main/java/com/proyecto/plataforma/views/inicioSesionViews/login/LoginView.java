package com.proyecto.plataforma.views.inicioSesionViews.login;

import com.proyecto.plataforma.capaNegocio.Admin;
import com.proyecto.plataforma.capaNegocio.Estudiante;
import com.proyecto.plataforma.capaNegocio.Profesor;
import com.proyecto.plataforma.capaNegocio.User;
import com.proyecto.plataforma.estructuras.*;
import com.proyecto.plataforma.views.alumnosviews.BuscarCursoVista;
import com.proyecto.plataforma.views.profesoresViews.creadorcurso.CreadorCursoView;
import com.proyecto.plataforma.views.adminViews.gestionusuario.GestionUsuarioView;
import com.proyecto.plataforma.views.inicioSesionViews.registro.RegistroView;
import com.proyecto.plataforma.views.inicioSesionViews.restablecerpassword.RestablecerPasswordView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "")
@PageTitle("Login | Proyecto Plataforma Aprendizaje")
public class    LoginView extends VerticalLayout {

    private final EstudianteLista estudianteLista;
    private final ProfesorLista profesorLista;
    private final AdminLista adminLista;

    @Autowired
    public LoginView(EstudianteLista estudianteLista, ProfesorLista profesorLista, AdminLista adminLista) {
        this.estudianteLista = estudianteLista;
        this.profesorLista = profesorLista;
        this.adminLista = adminLista;

        // Método para cargar los estudiantes y profesores desde MongoDB
        estudianteLista.cargarEstudiantes();
        profesorLista.cargarProfesor();
        adminLista.cargarAdmin();

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
            Profesor profesor = profesorLista.buscarProfesorCorreo(correo);
            Estudiante estudiante = estudianteLista.buscarEstudianteCorreo(correo);
            Admin admin = adminLista.buscarAdminCorreo(correo);

            if (profesor != null && profesor.getContraseña().equals(contraseña)) {
                VaadinSession.getCurrent().setAttribute(User.class, profesor);
                VaadinSession.getCurrent().setAttribute(Profesor.class, profesor);
                Notification.show("Inicio de sesión exitoso!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(CreadorCursoView.class));
            } else if (estudiante != null && estudiante.getContraseña().equals(contraseña)) {
                VaadinSession.getCurrent().setAttribute(User.class, estudiante);
                VaadinSession.getCurrent().setAttribute(Estudiante.class, estudiante);
                Notification.show("Inicio de sesión exitoso!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(BuscarCursoVista.class));
            } else if (admin != null && admin.getContraseña().equals(contraseña)) {
                VaadinSession.getCurrent().setAttribute(User.class, admin);
                VaadinSession.getCurrent().setAttribute(Admin.class, admin);
                Notification.show("Inicio de sesión exitoso!", 3000, Notification.Position.MIDDLE);
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
//Final version

