package com.proyecto.plataforma.views.creadorcuestionario;

import com.proyecto.plataforma.data.Cuestionario;
import com.proyecto.plataforma.data.Pregunta;
import com.proyecto.plataforma.data.TipoPregunta;
import com.proyecto.plataforma.services.CuestionarioService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "creador-cuestionario", layout = MainLayout.class)
public class CreadorCuestionarioView extends VerticalLayout {

    private final CuestionarioService cuestionarioService;
    private List<Pregunta> preguntas;

    @Autowired
    public CreadorCuestionarioView(CuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
        this.preguntas = new ArrayList<>();

        TextField tituloField = new TextField("Cuestionario");
        TextArea preguntaField = new TextArea("Pregunta");

        Button verdaderoFalsoButton = new Button("Verdadero o Falso");
        Button opcionMultipleButton = new Button("Opción Múltiple");

        ComboBox<String> verdaderoFalsoComboBox = new ComboBox<>("Verdadero o Falso");
        verdaderoFalsoComboBox.setItems("Verdadero", "Falso");

        TextArea opcionesField = new TextArea("Opciones de Respuesta");
        TextField respuestaCorrectaField = new TextField("Respuesta Correcta");

        Button addPreguntaButton = new Button("Añadir Pregunta");
        Button saveCuestionarioButton = new Button("Guardar Cuestionario");

        verdaderoFalsoComboBox.setVisible(false);
        opcionesField.setVisible(false);
        respuestaCorrectaField.setVisible(false);

        verdaderoFalsoButton.addClickListener(event -> {
            verdaderoFalsoComboBox.setVisible(true);
            opcionesField.setVisible(false);
            respuestaCorrectaField.setVisible(false);
        });

        opcionMultipleButton.addClickListener(event -> {
            verdaderoFalsoComboBox.setVisible(false);
            opcionesField.setVisible(true);
            respuestaCorrectaField.setVisible(true);
        });

        addPreguntaButton.addClickListener(event -> {
            String textoPregunta = preguntaField.getValue();
            TipoPregunta tipoPregunta = verdaderoFalsoComboBox.isVisible() ? TipoPregunta.VERDADERO_FALSO : TipoPregunta.OPCION_MULTIPLE;
            String respuestaCorrecta = "";
            List<String> opciones = new ArrayList<>();

            if (textoPregunta == null || textoPregunta.trim().isEmpty()) {
                Notification.show("La pregunta no puede estar vacía", 3000, Notification.Position.MIDDLE);
                return;
            }

            if (tipoPregunta == TipoPregunta.VERDADERO_FALSO) {
                respuestaCorrecta = verdaderoFalsoComboBox.getValue();
                if (respuestaCorrecta == null) {
                    Notification.show("Debe seleccionar una opción para Verdadero o Falso", 3000, Notification.Position.MIDDLE);
                    return;
                }
                opciones.add("Verdadero");
                opciones.add("Falso");
            } else if (tipoPregunta == TipoPregunta.OPCION_MULTIPLE) {
                String opcionesText = opcionesField.getValue();
                if (opcionesText == null || opcionesText.trim().isEmpty()) {
                    Notification.show("Debe ingresar opciones para Opción Múltiple", 3000, Notification.Position.MIDDLE);
                    return;
                }
                String[] opcionesArray = opcionesText.split(",");
                for (String opcion : opcionesArray) {
                    opciones.add(opcion.trim());
                }
                respuestaCorrecta = respuestaCorrectaField.getValue();
                if (respuestaCorrecta == null || respuestaCorrecta.trim().isEmpty()) {
                    Notification.show("Debe ingresar una respuesta correcta para Opción Múltiple", 3000, Notification.Position.MIDDLE);
                    return;
                }
            }

            Pregunta pregunta = new Pregunta();
            pregunta.setTexto(textoPregunta);
            pregunta.setTipo(tipoPregunta);
            pregunta.setOpciones(opciones);
            pregunta.setRespuestaCorrecta(respuestaCorrecta);

            preguntas.add(pregunta);
            preguntaField.clear();
            verdaderoFalsoComboBox.clear();
            opcionesField.clear();
            respuestaCorrectaField.clear();
        });

        saveCuestionarioButton.addClickListener(event -> {
            String titulo = tituloField.getValue();
            if (titulo == null || titulo.trim().isEmpty()) {
                Notification.show("El título del cuestionario no puede estar vacío", 3000, Notification.Position.MIDDLE);
                return;
            }

            if (preguntas.isEmpty()) {
                Notification.show("Debe añadir al menos una pregunta", 3000, Notification.Position.MIDDLE);
                return;
            }

            Cuestionario cuestionario = new Cuestionario();
            cuestionario.setTitulo(titulo);
            cuestionario.setPreguntas(preguntas);

            cuestionarioService.saveCuestionario(cuestionario);
            Notification.show("Cuestionario guardado exitosamente", 3000, Notification.Position.MIDDLE);

            getUI().ifPresent(ui -> ui.navigate("gestor-cuestionario"));
        });

        HorizontalLayout buttonsLayout = new HorizontalLayout(verdaderoFalsoButton, opcionMultipleButton);
        add(tituloField, preguntaField, verdaderoFalsoComboBox, opcionesField, respuestaCorrectaField, buttonsLayout, addPreguntaButton, saveCuestionarioButton);
    }
}
