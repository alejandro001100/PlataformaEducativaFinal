package com.proyecto.plataforma.views.restablecerpassword;

import com.proyecto.plataforma.data.*;
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
   private final EstudianteLista estudianteLista;
    private final ProfesorLista profesorLista;
    private final AdminLista adminLista;
    private Estudiante estudianteActual;
    private Profesor profesorActual;
    private Admin adminActual;

    public RestablecerPasswordView(EstudianteLista estudianteLista, ProfesorLista profesorLista, AdminLista adminLista) {
        this.estudianteLista = estudianteLista;
        this.profesorLista = profesorLista;
        this.adminLista = adminLista;


        EmailField emailField = new EmailField("Correo electrónico");
        PasswordField newPasswordField = new PasswordField("Nueva contraseña");
        PasswordField confirmNewPasswordField = new PasswordField("Confirmar nueva contraseña");
        Button resetButton = new Button("Restablecer contraseña");
        Button changePasswordButton = new Button("Cambiar Contraseña");
        Button backButton = new Button("Regresar");

        resetButton.addClickListener(event -> {
            String correo = emailField.getValue();

            //Busca si el correo ingresado pertenece a un estudiante
            estudianteActual = estudianteLista.buscarEstudianteCorreo(correo);

            //Busca si el correo ingresado pertenece a un profesor
            profesorActual = profesorLista.buscarProfesorCorreo(correo);

            //Busca si el correo ingresado pertenece a un admin
            adminActual= adminLista.buscarAdminCorreo(correo);


            //Si el correo ingresado pertenece a un estudiante
            if (estudianteActual != null) {
                Notification.show("Bienvenido, " + estudianteActual.getNombre() + " " + estudianteActual.getApellido(), 3000, Notification.Position.MIDDLE);
                emailField.setVisible(false);
                resetButton.setVisible(false);
                newPasswordField.setVisible(true);
                confirmNewPasswordField.setVisible(true);
                changePasswordButton.setVisible(true);
                backButton.setVisible(true);
            }
            //Si el correo ingresado pertenece a un profesor
            else if (profesorActual!= null) {
                Notification.show("Bienvenido, " + profesorActual.getNombre() + " " + profesorActual.getApellido(), 3000, Notification.Position.MIDDLE);
                emailField.setVisible(false);
                resetButton.setVisible(false);
                newPasswordField.setVisible(true);
                confirmNewPasswordField.setVisible(true);
                changePasswordButton.setVisible(true);
                backButton.setVisible(true);
            }
            else if (adminActual!= null) {
                Notification.show("Bienvenido, " + adminActual.getNombre() + " " + adminActual.getApellido(), 3000, Notification.Position.MIDDLE);
                emailField.setVisible(false);
                resetButton.setVisible(false);
                newPasswordField.setVisible(true);
                confirmNewPasswordField.setVisible(true);
                changePasswordButton.setVisible(true);
                backButton.setVisible(true);

            }
            //Si es que el correo no existe
            else {
                Notification.show("No se encontró una cuenta con ese correo.", 3000, Notification.Position.MIDDLE);
            }
        });

        changePasswordButton.addClickListener(eventChange -> {
            //Se obtienen la primera nueva contraseña
            String nuevaContrasena = newPasswordField.getValue();
            //Se obtiene la confirmación de la nueva contraseña
            String confirmarNuevaContrasena = confirmNewPasswordField.getValue();


            if (nuevaContrasena.isEmpty() || confirmarNuevaContrasena.isEmpty()) {
                Notification.show("Los campos de contraseña no pueden estar vacíos.", 3000, Notification.Position.MIDDLE);
                return;
            }
            if (nuevaContrasena.equals(confirmarNuevaContrasena)&&estudianteActual!=null) {
                estudianteActual.setContraseña(nuevaContrasena);
                estudianteLista.agregarEstudiante(estudianteActual);
                Notification.show("Contraseña cambiada exitosamente!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(LoginView.class));
            } else if (nuevaContrasena.equals(confirmarNuevaContrasena)&&profesorActual!=null) {
                profesorActual.setContraseña(nuevaContrasena);
                profesorLista.agregarProfesor(profesorActual);
                Notification.show("Contraseña cambiada exitosamente!", 3000, Notification.Position.MIDDLE);
                getUI().ifPresent(ui -> ui.navigate(LoginView.class));

            } else if (nuevaContrasena.equals(confirmarNuevaContrasena)&&adminActual!=null) {
                adminActual.setContraseña(nuevaContrasena);
                adminLista.agregarAdmin(adminActual);
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
    }
}
