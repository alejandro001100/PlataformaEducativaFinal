package com.proyecto.plataforma.conexionMongoDB.services;

import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.capaNegocio.Estudiante;
import com.proyecto.plataforma.conexionMongoDB.repository.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursosService {

    @Autowired
    private CursosRepository cursosRepository;

    public Cursos saveCursos(Cursos cursos) {
        return cursosRepository.save(cursos);
    }

    public Cursos encontrarPorTitulo(String titulo) {
        return cursosRepository.findByTitulo(titulo);
    }

    public Iterable<Cursos> encontrarTodos() {
        return cursosRepository.findAll();
    }

    public void eliminar(Cursos cursos) {
        cursosRepository.delete(cursos);
    }

    public Optional<Cursos> encontrarPorId(String id) {
        return cursosRepository.findById(id);
    }

    public List<Cursos> buscarPorTema(String tema) {
        return cursosRepository.findByTema(tema);
    }

    public List<Cursos> buscarPorProfesor(String profesorId) {
        return cursosRepository.findByProfesorId(profesorId);
    }

    public List<Cursos> buscarPorTemaYProfesor(String tema, String profesorId) {
        return cursosRepository.findByTemaAndProfesorId(tema, profesorId);
    }

    public Cursos agregarAlumnoAlCurso(String cursoId, Estudiante alumno) {
        Cursos curso = cursosRepository.findById(cursoId).orElse(null);
        if (curso != null) {
            curso.agregarAlumnoRegistrado(alumno);
            return cursosRepository.save(curso);
        }
        return null;
    }
}
