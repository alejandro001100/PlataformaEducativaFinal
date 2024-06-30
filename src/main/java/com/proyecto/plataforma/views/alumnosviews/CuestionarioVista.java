package com.proyecto.plataforma.views.alumnosviews;

import com.proyecto.plataforma.data.Cuestionario;
import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.data.NotaCuestionario;
import com.proyecto.plataforma.data.Pregunta;
import com.proyecto.plataforma.services.CuestionarioService;
import com.proyecto.plataforma.services.EstudianteService;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Route(value = "cuestionario", layout = MainLayout.class)
@PageTitle("Cuestionario | Plataforma Educativa")
public class CuestionarioVista extends VerticalLayout implements HasUrlParameter<String> {

    private final CuestionarioService cuestionarioService;
    private final EstudianteService estudianteService;
    private Cuestionario cuestionario;
    private final Map<Pregunta, String> respuestas = new HashMap<>();
    private Estudiante estudiante;
    private int tiempoRestante;
    private Timer timer;
    private Paragraph tiempoRestanteParagraph;

    @Autowired
    public CuestionarioVista(CuestionarioService cuestionarioService, EstudianteService estudianteService) {
        this.cuestionarioService = cuestionarioService;
        this.estudianteService = estudianteService;
        addClassName("list-view");
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        System.out.println("Received parameter: " + parameter); // Debug
        if (parameter != null && !parameter.isEmpty()) {
            Optional<Cuestionario> optionalCuestionario = cuestionarioService.findById(parameter);
            if (optionalCuestionario.isPresent()) {
                this.cuestionario = optionalCuestionario.get();
                this.estudiante = getCurrentEstudiante();
                if (this.estudiante != null) {
                    NotaCuestionario notaCuestionario = this.estudiante.getNotaCuestionario(this.cuestionario.getTitulo());
                    if (notaCuestionario != null && notaCuestionario.getIntentos() >= this.cuestionario.getIntentos()) {
                        add(new H2("Has alcanzado el número máximo de intentos para este cuestionario"));
                    } else {
                        mostrarCuestionario();
                    }
                } else {
                    add(new H2("Estudiante no encontrado"));
                }
            } else {
                add(new H2("Cuestionario no encontrado"));
            }
        } else {
            add(new H2("Cuestionario no encontrado"));
        }
    }

    private void mostrarCuestionario() {
        if (cuestionario != null) {
            add(new H2("Cuestionario: " + cuestionario.getTitulo()));
            add(new Paragraph("Tiempo de examen: " + cuestionario.getTiempoExamen() + " minutos"));
            add(new Paragraph("Número de intentos: " + cuestionario.getIntentos()));

            tiempoRestante = cuestionario.getTiempoExamen() * 60;
            tiempoRestanteParagraph = new Paragraph("Tiempo restante: " + formatTime(tiempoRestante));
            add(tiempoRestanteParagraph);
            iniciarTemporizador();

            cuestionario.getPreguntas().forEach(pregunta -> {
                add(new Paragraph("Pregunta: " + pregunta.getTexto()));
                if (pregunta.getTipo() == com.proyecto.plataforma.data.TipoPregunta.VERDADERO_FALSO) {
                    RadioButtonGroup<String> options = new RadioButtonGroup<>();
                    options.setItems("Verdadero", "Falso");
                    options.addValueChangeListener(event -> respuestas.put(pregunta, event.getValue()));
                    add(options);
                } else if (pregunta.getTipo() == com.proyecto.plataforma.data.TipoPregunta.OPCION_MULTIPLE) {
                    CheckboxGroup<String> options = new CheckboxGroup<>();
                    options.setItems(pregunta.getOpciones());
                    options.addValueChangeListener(event -> respuestas.put(pregunta, String.join(", ", event.getValue())));
                    add(options);
                }
            });

            Button finalizarCuestionarioButton = new Button("Finalizar Cuestionario");
            finalizarCuestionarioButton.addClickListener(e -> finalizarCuestionario());
            add(finalizarCuestionarioButton);
        }
    }

    private void iniciarTemporizador() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                tiempoRestante--;
                UI ui = getUI().orElse(null);
                if (ui != null) {
                    ui.access(() -> {
                        tiempoRestanteParagraph.setText("Tiempo restante: " + formatTime(tiempoRestante));
                        if (tiempoRestante <= 0) {
                            timer.cancel();
                            finalizarCuestionario();
                        }
                    });
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    private void finalizarCuestionario() {
        if (timer != null) {
            timer.cancel();
        }

        // Ensure all responses are recorded
        cuestionario.getPreguntas().forEach(pregunta -> {
            if (!respuestas.containsKey(pregunta)) {
                respuestas.put(pregunta, "");
            }
        });

        int nota = calcularNota();

        if (estudiante != null) {
            NotaCuestionario notaCuestionario = estudiante.getNotaCuestionario(cuestionario.getTitulo());
            if (notaCuestionario == null) {
                notaCuestionario = new NotaCuestionario(cuestionario.getTitulo(), nota, 1);
            } else {
                notaCuestionario.setNota(nota);
                notaCuestionario.setIntentos(notaCuestionario.getIntentos() + 1);
            }
            estudiante.agregarNotaCuestionario(notaCuestionario);
            estudianteService.saveEstudiante(estudiante);

            Notification.show("Cuestionario finalizado con nota: " + nota, 3000, Notification.Position.MIDDLE);
            getUI().ifPresent(ui -> ui.navigate("ver-notas"));
        } else {
            Notification.show("No se encontró el estudiante", 3000, Notification.Position.MIDDLE);
        }
    }

    private int calcularNota() {
        int nota = 0;
        for (Map.Entry<Pregunta, String> entry : respuestas.entrySet()) {
            Pregunta pregunta = entry.getKey();
            String respuesta = entry.getValue();
            if (pregunta.getTipo() == com.proyecto.plataforma.data.TipoPregunta.VERDADERO_FALSO && respuesta.equals(pregunta.getRespuestaCorrecta())) {
                nota += pregunta.getPuntaje();
            } else if (pregunta.getTipo() == com.proyecto.plataforma.data.TipoPregunta.OPCION_MULTIPLE) {
                if (respuesta.equals(pregunta.getRespuestaCorrecta())) {
                    nota += pregunta.getPuntaje();
                }
            }
        }
        return nota;
    }

    private Estudiante getCurrentEstudiante() {
        return (Estudiante) VaadinSession.getCurrent().getAttribute(Estudiante.class);
    }

    private String formatTime(int seconds) {
        int minutos = seconds / 60;
        int segundos = seconds % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
//Final version

