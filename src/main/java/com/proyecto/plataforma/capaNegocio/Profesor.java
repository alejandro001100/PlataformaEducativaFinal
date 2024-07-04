package com.proyecto.plataforma.capaNegocio;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends User {
    private String id;
    private List<Cursos> cursosCreados;
    private List<Cuestionario> cuestionariosCreados;

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

    public void addCursoCreado(Cursos curso) {
        this.cursosCreados.add(curso);
    }

    public List<Cuestionario> getCuestionariosCreados() {
        return cuestionariosCreados;
    }

    public Cursos buscarCurso(String idCurso) {
        for (Cursos curso : cursosCreados) {
            if (curso.getId().equals(idCurso)) {
                return curso;
            }
        }
        return null;
    }

    public void setCuestionariosCreados(List<Cuestionario> cuestionariosCreados) {
        this.cuestionariosCreados = cuestionariosCreados;
    }

    public void addCuestionarioCreado(Cuestionario cuestionario) {
        this.cuestionariosCreados.add(cuestionario);
    }
}
//Final version

