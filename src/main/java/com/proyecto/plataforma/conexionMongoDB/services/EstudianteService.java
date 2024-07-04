package com.proyecto.plataforma.conexionMongoDB.services;

import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.capaNegocio.Estudiante;
import com.proyecto.plataforma.conexionMongoDB.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante saveEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante findByCorreo(String correo) {
        return estudianteRepository.findByCorreo(correo);
    }

    public boolean isCorreoRegistrado(String correo) {
        return estudianteRepository.findByCorreo(correo) != null;
    }

    public Iterable<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    public void delete(Estudiante estudiante) {
        estudianteRepository.delete(estudiante);
    }

    public Estudiante agregarCursoTomado(String correoEstudiante, Cursos curso) {
        Estudiante estudiante = estudianteRepository.findByCorreo(correoEstudiante);
        if (estudiante != null) {
            estudiante.agregarCursoTomado(curso);
            return estudianteRepository.save(estudiante);
        }
        return null;
    }

    // Método para obtener estudiantes por curso
    public List<Estudiante> getAlumnosPorCurso(String cursoId) {
        return estudianteRepository.findByCursoId(cursoId);
    }
}
//Final version

