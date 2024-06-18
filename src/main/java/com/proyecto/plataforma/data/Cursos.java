package com.proyecto.plataforma.data;

import com.vaadin.flow.component.template.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "cursos")
public class Cursos implements Comparable<Cursos> {
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private String area;
    private List<Capitulo> capitulos; // Añadir lista de capítulos

    public Cursos(String id, String titulo, String descripcion, String area) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.area = area;
        this.capitulos = new ArrayList<>(); // Inicializar lista de capítulos
    }

    // Getters y setters
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    @Override
    public int compareTo(Cursos o) {
        return this.titulo.compareToIgnoreCase(o.getTitulo());
    }
}
