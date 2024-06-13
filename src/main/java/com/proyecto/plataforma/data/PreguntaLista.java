package com.proyecto.plataforma.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PreguntaLista {

    private List<Pregunta> preguntaLista;

    public PreguntaLista() {
        this.preguntaLista = new ArrayList<>();
    }

    public List<Pregunta> getPreguntaLista() {
        return preguntaLista;
    }

    public void setPreguntaLista(List<Pregunta> preguntaLista) {
        this.preguntaLista = preguntaLista;
    }

    public void guardarPregunta(Pregunta pregunta) {
        preguntaLista.add(pregunta);
    }

    public void eliminarPregunta(Pregunta pregunta) {
        preguntaLista.remove(pregunta);
    }
}
