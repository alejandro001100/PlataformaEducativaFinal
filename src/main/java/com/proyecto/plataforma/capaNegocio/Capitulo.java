package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "capitulos")
public class Capitulo implements Comparable<Capitulo> {

    //Atributos
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


    //Compare to para la priority Qeue
    @Override
    public int compareTo(Capitulo o) {
        if (this.prioridad < o.prioridad) {
            return -1; // Coloca this antes que o
        } else if (this.prioridad > o.prioridad) {
            return 1; // Coloca o antes que this
        } else {
            return 0; // Son iguales en prioridad
        }
    }

}


