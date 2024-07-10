package com.proyecto.plataforma.conexionMongoDB.repository;

import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.capaNegocio.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CursosRepository extends MongoRepository<Cursos, String> {
        List<Cursos> findByTema(String tema);
        List<Cursos> findByProfesor(Profesor profesor);
        List<Cursos> findByTemaAndProfesor(String tema, Profesor profesor); // Cambiado de findByTemaAndAProfesor a findByTemaAndProfesor
        Cursos findByTitulo(String titulo);
}
