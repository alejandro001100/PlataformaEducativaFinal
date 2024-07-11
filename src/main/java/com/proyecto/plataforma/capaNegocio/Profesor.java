package com.proyecto.plataforma.capaNegocio;

import org.w3c.dom.Notation;

import java.util.ArrayList;
import java.util.List;

//Usuario tipo profesor, hereda de User
public class Profesor extends User {
    //Atributos
    private String id;
    private List<Cursos> cursosCreados;
    private List<Cuestionario> cuestionariosCreados;
    private List<Cuestionario> cuestionariosRendidos;
    private Estudiante[] estudiantes;

    //Constructores
    public Profesor(String nombre, String apellido, String correo, String contraseña, String rol, String id) {
        super(nombre, apellido, correo, contraseña, rol);
        this.id = id;
        this.cursosCreados = new ArrayList<>();
        this.cuestionariosCreados = new ArrayList<>();
    }

    public Profesor() {
        super();
        this.cursosCreados = new ArrayList<>();
        this.cuestionariosCreados = new ArrayList<>();
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Cursos> getCursosCreados() {
        return cursosCreados;
    }

    public void setCursosCreados(List<Cursos> cursosCreados) {
        this.cursosCreados = cursosCreados;
    }

    public List<Cuestionario> getCuestionariosCreados() {
        return cuestionariosCreados;
    }

    public void setCuestionariosCreados(List<Cuestionario> cuestionariosCreados) {
        this.cuestionariosCreados = cuestionariosCreados;
    }

    //Metodos
    public void addCursoCreado(Cursos curso) {
        this.cursosCreados.add(curso);
    }

    public Cursos buscarCurso(String idCurso) {
        for (Cursos curso : cursosCreados) {
            if (curso.getId().equals(idCurso)) {
                return curso;
            }
        }
        return null;
    }
    public Cuestionario buscarCuestionario(String titulo) {
        for (Cuestionario cuestionario : cuestionariosCreados) {
            if (cuestionario.getTitulo().equalsIgnoreCase(titulo)) {
                return cuestionario;
            }
        }
        return null;
    }

    // Método para buscar un estudiante por nombre
    public Estudiante buscarEstudiante(String nombre) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equalsIgnoreCase(nombre)) {
                return estudiante; // Retorna el estudiante si se encuentra
            }
        }
        return null; // Retorna null si no se encuentra el estudiante
    }
    // Método fantasma para buscar un estudiante por nombre
    public Estudiante buscarEstudianteFalso(String nombre) {
        // Método fantasma para buscar estudiante (sin operación real)
        return null; // Siempre retorna null
    }


    public List<Estudiante> verEstudiantes() {

        return new ArrayList<>();
    }


    public void addCuestionarioCreado(Cuestionario cuestionario) {
        this.cuestionariosCreados.add(cuestionario);
    }

}
//Final version

