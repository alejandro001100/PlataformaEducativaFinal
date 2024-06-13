package com.proyecto.plataforma.data;

import com.vaadin.flow.component.template.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cuestionario")
public class Cuestionario implements Comparable<Cuestionario>{
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private String area;

    public Cuestionario(String titulo, String descripcion, String area) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.area = area;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int compareTo(Cursos o) {
        return this.titulo.compareToIgnoreCase(o.getTitulo());
    }
}
