package com.proyecto.plataforma.data;

import com.proyecto.plataforma.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstudianteLista {

    private List<Estudiante> estudianteLista;
    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteLista(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
        this.estudianteLista = new ArrayList<>();
        cargarEstudiantes();
    }

    public List<Estudiante> getEstudianteLista() {
        return estudianteLista;
    }

    public void setEstudianteLista(List<Estudiante> estudianteLista) {
        this.estudianteLista = estudianteLista;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudianteLista.add(estudiante);
        estudianteService.saveEstudiante(estudiante);
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteLista.remove(estudiante);
        estudianteService.delete(estudiante);
    }

    public void cargarEstudiantes() {
        List<Estudiante> estudiantes = (List<Estudiante>) estudianteService.findAll();
        estudianteLista.addAll(estudiantes);
    }

    public Estudiante buscarEstudianteCorreo(String correo) {
        for (Estudiante e : estudianteLista) {
            if (e.getCorreo().equals(correo)) {
                return e;
            }
        }
        return null;
    }

    public boolean isCorreoRegistrado(String correo) {
        for (Estudiante e : estudianteLista) {
            if (e.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public List<Estudiante> getAllEstudiantes() {
        return estudianteLista;
    }

    public void agregarCursoTomado(String correoEstudiante, Cursos curso) {
        Estudiante estudiante = buscarEstudianteCorreo(correoEstudiante);
        if (estudiante != null) {
            estudiante.agregarCursoTomado(curso);
            estudianteService.saveEstudiante(estudiante);
        }
    }

    public void eliminarCursoTomado(String correoEstudiante, Cursos curso) {
        Estudiante estudiante = buscarEstudianteCorreo(correoEstudiante);
        if (estudiante != null) {
            estudiante.eliminarCursoTomado(curso);
            estudianteService.saveEstudiante(estudiante);
        }
    }
}
//Final version

