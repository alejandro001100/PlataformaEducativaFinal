package com.proyecto.plataforma.views.alumnosviews;

import com.proyecto.plataforma.capaNegocio.ArbolDecision;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "arbol-decision", layout = MainLayout.class)
@PageTitle("Árbol de Decisión | Plataforma Educativa")
public class ArbolDecisionView extends VerticalLayout {

    private ArbolDecision.Nodo nodoActual;
    private ArbolDecision arbolDecision;

    public ArbolDecisionView() {
        this.arbolDecision = new ArbolDecision();
        this.nodoActual = arbolDecision.getRaiz();

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        removeAll();
        add(new H2("Encuentra tu área"));
        if (nodoActual.esHoja()) {
            mostrarResultado();
        } else {
            add(new H2(nodoActual.getPreguntaOArea()));
            HorizontalLayout botonesLayout = new HorizontalLayout();
            Button botonSi = new Button("Sí");
            botonSi.getStyle().set("background-color", "#4CAF50");
            botonSi.getStyle().set("color", "white");
            Button botonNo = new Button("No");
            botonNo.getStyle().set("background-color", "#f44336");
            botonNo.getStyle().set("color", "white");

            botonSi.addClickListener(e -> {
                nodoActual = nodoActual.getNodoSi();
                mostrarPregunta();
            });

            botonNo.addClickListener(e -> {
                nodoActual = nodoActual.getNodoNo();
                mostrarPregunta();
            });

            botonesLayout.add(botonSi, botonNo);
            add(botonesLayout);
        }
    }

    private void mostrarResultado() {
        removeAll();
        H2 mensaje = new H2("Tu área escogida es: " + nodoActual.getPreguntaOArea());
        mensaje.getStyle().set("color", "#4CAF50");
        mensaje.getStyle().set("text-align", "center");
        add(mensaje);

        Button botonReiniciar = new Button("Volver a Intentar");
        botonReiniciar.getStyle().set("background-color", "#2196F3");
        botonReiniciar.getStyle().set("color", "white");
        botonReiniciar.addClickListener(e -> {
            nodoActual = arbolDecision.getRaiz();
            mostrarPregunta();
        });

        add(botonReiniciar);
    }
}
