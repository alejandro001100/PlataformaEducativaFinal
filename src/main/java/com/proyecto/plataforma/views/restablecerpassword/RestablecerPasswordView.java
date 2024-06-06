package com.proyecto.plataforma.views.restablecerpassword;

import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.data.Profesor;
import com.proyecto.plataforma.data.User;
import com.proyecto.plataforma.services.EstudianteService;
import com.proyecto.plataforma.services.ProfesorService;
import com.proyecto.plataforma.services.UserService;
import com.proyecto.plataforma.views.MainLayout;
import com.proyecto.plataforma.views.login.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Restablecer Password")
@Route(value = "restablecer-password", layout = MainLayout.class)
public class RestablecerPasswordView extends VerticalLayout {
    private final EstudianteService estudianteService;
    private final ProfesorService profesorService;
    private Estudiante estudianteActual;
    private Profesor profesorActual;

    public RestablecerPasswordView(EstudianteService estudianteService, ProfesorService profesorService) {
        this.estudianteService = estudianteService;
        this.profesorService = profesorService;

        EmailField emailField = new EmailField("Correo electrónico");
        PasswordField newPasswordField = new PasswordField("Nueva contraseña");
        PasswordField confirmNewPasswordField = new PasswordField("Confirmar nueva contraseña");
        Button resetButton = new Button("Restablecer contraseña");
        Button changePasswordButton = new Button("Cambiar Contraseña");
        Button backButton = new Button("Regresar");

        resetButton.addClickListener(event -> {
            String correo = emailField.getValue();
            estudianteActual = estudianteService.findByCorreo(correo);
            profesorActual = profesorService.findByCorreo(correo);
            if (estudianteActual != null) {
                Notification.show("Bienvenido, " + estudianteActual.getNombre() + " " + estudianteActual.getApellido(), 3000, Notification.Position.MIDDLE);
                emailField.setVisible(false);
                resetButton.setVisible(false);
                newPasswordField.setVisible(true);
                confirmNewPasswordField.setVisible(true);
                changePasswordButton.setVisible(true);
                backButton.setVisible(true);
            } else if (profesorActual!= null) {
                Notification.show("Bienvenido, " + profesorActual.getNombre() + " " + profesorActual.getApellido(), 3000, Notification.Position.MIDDLE);
                emailField.setVisible(false);
                resetButton.setVisible(false);
                newPasswordField.setVisible(true);
                confirmNewPasswordField.setVisible(true);
                changePasswordButton.setVisible(true);
                backButton.setVisible(true);
            } else {
                Notification.show("No se encontró una cuenta con ese correo.", 3000, Notification.Position.MIDDLE);
            }
        });

        changePasswordButton.addClickListener(eventChange -> {
            String nuevaContrasena = newPasswordField.getValue();
            String confirmarNuevaContrasena = confirmNewPasswordField.getValue();
            if (nuevaContrasena.isEmpty() || confirmarNuevaContrasena.isEmpty()) {
                Notification.show("Los campos de contraseña no pueden estar vacíos.", 3000, Notification.Position.MIDDLE);
                return;
            }
            if (nuevaContrasena.equals(confirmarNuevaContrasena)&&estudianteActual!=null) {
                estudianteActual.setContraseña(nuevaContrasena);
                estudianteService.saveEstudiante(estudianteActual);
                Notification.show("Contraseña cambiada exitosamente!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(LoginView.class));
            } else if (nuevaContrasena.equals(confirmarNuevaContrasena)&&profesorActual!=null) {
                profesorActual.setContraseña(nuevaContrasena);
                profesorService.saveProfesor(profesorActual);
                Notification.show("Contraseña cambiada exitosamente!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(LoginView.class));

            } else {
                Notification.show("Las contraseñas no coinciden.", 3000, Notification.Position.MIDDLE);
            }
        });

        backButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        });

        resetButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        changePasswordButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        backButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        newPasswordField.setVisible(false);
        confirmNewPasswordField.setVisible(false);
        changePasswordButton.setVisible(false);
        backButton.setVisible(false);

        add(new H3("Restablecer tu contraseña"), emailField, resetButton, newPasswordField, confirmNewPasswordField, changePasswordButton, backButton);
    }}