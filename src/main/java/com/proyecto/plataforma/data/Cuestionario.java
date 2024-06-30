package com.proyecto.plataforma.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cuestionarios")
public class Cuestionario {
    @Id
    private String id;
    private String titulo;
    private List<Pregunta> preguntas;
    private int puntajeTotal;
    private int tiempoExamen; // en minutos

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public int getTiempoExamen() {
        return tiempoExamen;
    }

    public void setTiempoExamen(int tiempoExamen) {
        this.tiempoExamen = tiempoExamen;
    }
}
//Final version

