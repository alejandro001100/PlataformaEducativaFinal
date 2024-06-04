package com.proyecto.plataforma.views;

import com.proyecto.plataforma.views.creadorcuestionario.CreadorCuestionarioView;
import com.proyecto.plataforma.views.creadorcurso.CreadorCursoView;
import com.proyecto.plataforma.views.gestionusuario.GestionUsuarioView;
import com.proyecto.plataforma.views.gestorclases.GestorClasesView;
import com.proyecto.plataforma.views.gestorcuestionario.GestorCuestionarioView;
import com.proyecto.plataforma.views.login.LoginView;
import com.proyecto.plataforma.views.registro.RegistroView;
import com.proyecto.plataforma.views.restablecerpassword.RestablecerPasswordView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span("Proyecto Plataforma Aprendizaje");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Registro", RegistroView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Login", LoginView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Restablecer Password", RestablecerPasswordView.class,
                LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Gestion Usuario", GestionUsuarioView.class,
                LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(
                new SideNavItem("Gestor Clases", GestorClasesView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(
                new SideNavItem("Creador Curso", CreadorCursoView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Gestor Cuestionario", GestorCuestionarioView.class,
                LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Creador Cuestionario", CreadorCuestionarioView.class,
                LineAwesomeIcon.PENCIL_RULER_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
