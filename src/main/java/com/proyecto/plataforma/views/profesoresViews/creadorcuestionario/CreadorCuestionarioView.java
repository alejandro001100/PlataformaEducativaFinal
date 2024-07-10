package com.proyecto.plataforma.views.profesoresViews.creadorcuestionario;

import com.proyecto.plataforma.capaNegocio.Cuestionario;
import com.proyecto.plataforma.capaNegocio.Pregunta;
import com.proyecto.plataforma.capaNegocio.Profesor;
import com.proyecto.plataforma.capaNegocio.TipoPregunta;
import com.proyecto.plataforma.conexionMongoDB.services.CuestionarioService;
import com.proyecto.plataforma.conexionMongoDB.services.ProfesorService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(value = "crear-cuestionario", layout = MainLayout.class)
public class CreadorCuestionarioView extends VerticalLayout {

    private final CuestionarioService cuestionarioService;
    private final ProfesorService profesorService;
    private final TextField tituloField;
    private final ComboBox<Integer> tiempoExamenComboBox;
    private final TextField cantidadPreguntasField;
    private final ComboBox<Integer> puntajeTotalComboBox;
    private final ComboBox<Integer> intentosComboBox;
    private final Button generarPreguntasButton;
    private final List<PreguntaLayout> preguntasLayouts = new ArrayList<>();

    @Autowired
    public CreadorCuestionarioView(CuestionarioService cuestionarioService, ProfesorService profesorService) {
        this.cuestionarioService = cuestionarioService;
        this.profesorService = profesorService;
        this.tituloField = new TextField("Título del Cuestionario");
        this.tiempoExamenComboBox = new ComboBox<>("Tiempo del Examen (minutos)");
        this.cantidadPreguntasField = new TextField("Cantidad de Preguntas");
        this.puntajeTotalComboBox = new ComboBox<>("Puntaje Total del Examen");
        this.intentosComboBox = new ComboBox<>("Intentos del Examen");
        this.generarPreguntasButton = new Button("Generar Preguntas");

        tiempoExamenComboBox.setItems(5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60);
        puntajeTotalComboBox.setItems(10, 20);
        intentosComboBox.setItems(1, 2, 3);

        generarPreguntasButton.addClickListener(event -> generarPreguntas());

        add(tituloField, tiempoExamenComboBox, cantidadPreguntasField, puntajeTotalComboBox, intentosComboBox, generarPreguntasButton);
    }

    private void generarPreguntas() {
        preguntasLayouts.clear();
        removeAll();
        add(tituloField, tiempoExamenComboBox, cantidadPreguntasField, puntajeTotalComboBox, intentosComboBox, generarPreguntasButton);

        int cantidadPreguntas;
        int puntajeTotal;
        int intentos;

        try {
            cantidadPreguntas = Integer.parseInt(cantidadPreguntasField.getValue());
            puntajeTotal = puntajeTotalComboBox.getValue();
            intentos = intentosComboBox.getValue();
        } catch (NumberFormatException | NullPointerException e) {
            Notification.show("Ingrese valores válidos para la cantidad de preguntas, el puntaje total y el número de intentos");
            return;
        }

        int puntajePorPregunta = puntajeTotal / cantidadPreguntas;

        for (int i = 0; i < cantidadPreguntas; i++) {
            PreguntaLayout preguntaLayout = new PreguntaLayout(i + 1, puntajePorPregunta, puntajeTotal);
            preguntasLayouts.add(preguntaLayout);
            add(preguntaLayout);
        }

        Button saveCuestionarioButton = new Button("Guardar Cuestionario", event -> guardarCuestionario(puntajeTotal));
        add(saveCuestionarioButton);
    }

    private void guardarCuestionario(int puntajeTotal) {
        List<Pregunta> preguntas = new ArrayList<>();
        int totalPuntaje = 0;

        for (PreguntaLayout layout : preguntasLayouts) {
            Pregunta pregunta = layout.getPregunta();
            preguntas.add(pregunta);
            totalPuntaje += pregunta.getPuntaje();
        }

        if (totalPuntaje != puntajeTotal) {
            Notification.show("El puntaje total asignado a las preguntas no coincide con el puntaje total del examen.");
            return;
        }

        Cuestionario cuestionario = new Cuestionario();
        cuestionario.setTitulo(tituloField.getValue());
        cuestionario.setTiempoExamen(tiempoExamenComboBox.getValue());
        cuestionario.setPreguntas(preguntas);
        cuestionario.setPuntajeTotal(puntajeTotal);
        cuestionario.setIntentos(intentosComboBox.getValue());
        Profesor currentUser = VaadinSession.getCurrent().getAttribute(Profesor.class);
        Profesor profesor = new Profesor(currentUser.getNombre(), currentUser.getApellido(), currentUser.getCorreo(), currentUser.getContraseña(), currentUser.getRol(), currentUser.getId() );
        cuestionario.setProfesor(profesor);
        currentUser.addCuestionarioCreado(cuestionario);
        profesorService.saveProfesor(currentUser);
        cuestionarioService.saveCuestionario(cuestionario);



        Notification.show("Cuestionario guardado exitosamente.");
        resetFormulario();
    }

    private void resetFormulario() {
        tituloField.clear();
        tiempoExamenComboBox.clear();
        cantidadPreguntasField.clear();
        puntajeTotalComboBox.clear();
        intentosComboBox.clear();
        preguntasLayouts.clear();
        removeAll();
        add(tituloField, tiempoExamenComboBox, cantidadPreguntasField, puntajeTotalComboBox, intentosComboBox, generarPreguntasButton);
    }

    private class PreguntaLayout extends VerticalLayout {
        private final TextField preguntaField;
        private final ComboBox<TipoPregunta> tipoPreguntaComboBox;
        private final TextArea opcionesField;
        private final ComboBox<String> respuestaCorrectaComboBox;
        private final ComboBox<Integer> puntajeField;
        private final int puntajeTotal;

        public PreguntaLayout(int numeroPregunta, int puntajePorPregunta, int puntajeTotal) {
            this.puntajeTotal = puntajeTotal;
            preguntaField = new TextField("Pregunta " + numeroPregunta);
            tipoPreguntaComboBox = new ComboBox<>("Tipo de Pregunta", TipoPregunta.values());
            opcionesField = new TextArea("Opciones (separadas por comas)");
            respuestaCorrectaComboBox = new ComboBox<>("Respuesta Correcta");
            puntajeField = new ComboBox<>("Puntaje");

            if (puntajeTotal == 10) {
                puntajeField.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            } else if (puntajeTotal == 20) {
                puntajeField.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
            }

            puntajeField.setValue(puntajePorPregunta);

            tipoPreguntaComboBox.addValueChangeListener(event -> configurarOpciones());
            opcionesField.addValueChangeListener(event -> actualizarOpcionesRespuestaCorrecta());

            add(preguntaField, tipoPreguntaComboBox, opcionesField, respuestaCorrectaComboBox, puntajeField);
        }

        private void configurarOpciones() {
            TipoPregunta tipoPregunta = tipoPreguntaComboBox.getValue();
            if (tipoPregunta == TipoPregunta.VERDADERO_FALSO) {
                opcionesField.setValue("Verdadero, Falso");
                respuestaCorrectaComboBox.setItems("Verdadero", "Falso");
            } else if (tipoPregunta == TipoPregunta.OPCION_MULTIPLE) {
                actualizarOpcionesRespuestaCorrecta();
            } else {
                opcionesField.clear();
                respuestaCorrectaComboBox.clear();
            }
        }

        private void actualizarOpcionesRespuestaCorrecta() {
            String[] opciones = opcionesField.getValue().split(",\\s*");
            respuestaCorrectaComboBox.setItems(opciones);
        }

        public Pregunta getPregunta() {
            Pregunta pregunta = new Pregunta();
            pregunta.setTexto(preguntaField.getValue());
            pregunta.setTipo(tipoPreguntaComboBox.getValue());
            pregunta.setOpciones(Arrays.asList(opcionesField.getValue().split(",\\s*")));
            pregunta.setRespuestaCorrecta(respuestaCorrectaComboBox.getValue());
            pregunta.setPuntaje(puntajeField.getValue());
            return pregunta;
        }
    }
}
//Final version