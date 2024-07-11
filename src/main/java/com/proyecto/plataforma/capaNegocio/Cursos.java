package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Document(collection = "cursos")
public class Cursos {
    //Atributos
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private String area;
    private String tema;
    private Profesor profesor;
    private PriorityQueue<Capitulo> capitulos;

    @DBRef
    private List<Estudiante> alumnosRegistrados;


    //Constructor
    public Cursos(String id, String titulo, String descripcion, String area, String tema, Profesor profesor) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.area = area;
        this.tema = tema;
        this.profesor = profesor;
        this.capitulos = new PriorityQueue<>();
        this.alumnosRegistrados = new ArrayList<>();
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

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Profesor getProfesor() { // Cambiado de getProfesor a getProfesorId
        return profesor;
    }

    public void setProfesor(Profesor profesor) { // Cambiado de setProfesor a setProfesorId
        this.profesor = profesor;
    }

    public PriorityQueue<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(PriorityQueue<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    public List<Estudiante> getAlumnosRegistrados() {
        return alumnosRegistrados;
    }

    public void setAlumnosRegistrados(List<Estudiante> alumnosRegistrados) {
        this.alumnosRegistrados = alumnosRegistrados;
    }

    //Metodo

    public void agregarAlumnoRegistrado(Estudiante alumno) {
        this.alumnosRegistrados.add(alumno);
    }
}
