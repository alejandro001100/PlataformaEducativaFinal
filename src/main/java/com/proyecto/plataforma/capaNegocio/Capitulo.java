package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "capitulos")
public class Capitulo implements Comparable<Capitulo> {
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private int prioridad;

    @DBRef
    private Cuestionario cuestionario;

    // Constructor vacío
    public Capitulo() {
    }

    // Constructor con parámetros
    public Capitulo(String titulo, String descripcion, int prioridad, Cuestionario cuestionario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.cuestionario = cuestionario;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    @Override
    public int compareTo(Capitulo o) {
        return Integer.compare(this.prioridad, o.prioridad);
    }
}
//Final version

