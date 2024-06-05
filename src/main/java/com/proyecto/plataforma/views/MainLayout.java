package com.proyecto.plataforma.views;

import com.proyecto.plataforma.data.User;
import com.proyecto.plataforma.views.gestionusuario.GestionUsuarioView;
import com.proyecto.plataforma.views.creadorcurso.CreadorCursoView;
import com.proyecto.plataforma.views.login.LoginView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Proyecto Plataforma Aprendizaje");
        logo.addClassNames("text-l", "m-m");

        Button logoutButton = new Button("Logout", e -> {
            VaadinSession.getCurrent().getSession().invalidate();
            VaadinSession.getCurrent().close();
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        });

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logoutButton);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        User user = VaadinSession.getCurrent().getAttribute(User.class);
        if (user != null) {
            if ("ADMIN".equals(user.getRol())) {
                addToDrawer(new VerticalLayout(
                        new RouterLink("Gestión de Usuarios", GestionUsuarioView.class)
                ));
            } else if ("PROFESOR".equals(user.getRol())) {
                addToDrawer(new VerticalLayout(
                        new RouterLink("Creador de Cursos", CreadorCursoView.class)
                ));
            } else if ("ESTUDIANTE".equals(user.getRol())) {
                addToDrawer(new VerticalLayout(
                        new RouterLink("Cursos", CreadorCursoView.class)
                ));
            }
        } else {
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        }
    }
}
