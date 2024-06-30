package com.proyecto.plataforma.services;

import com.proyecto.plataforma.data.Cursos;
import com.proyecto.plataforma.repository.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursosService {
    @Autowired
    private CursosRepository cursosRepository;

    public Cursos saveCursos(Cursos cursos) {
        return cursosRepository.save(cursos);
    }

    public Cursos findByTitulo(String titulo) {
        return cursosRepository.findByTitulo(titulo);
    }

    public boolean isTituloRegistrado(String titulo) {
        return cursosRepository.findByTitulo(titulo) != null;
    }

    public Iterable<Cursos> findAll() {
        return cursosRepository.findAll();
    }

    public void delete(Cursos cursos) {
        cursosRepository.delete(cursos);
    }

    public Optional<Cursos> findById(String id) {
        return cursosRepository.findById(id);
    }
}
//Final version

