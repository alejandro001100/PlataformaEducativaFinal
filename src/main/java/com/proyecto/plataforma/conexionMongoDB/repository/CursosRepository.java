package com.proyecto.plataforma.conexionMongoDB.repository;

import com.proyecto.plataforma.capaNegocio.Cursos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CursosRepository extends MongoRepository<Cursos, String> {
        List<Cursos> findByTema(String tema);
        List<Cursos> findByAutor(String autor);
        List<Cursos> findByTemaAndAutor(String tema, String autor);
        Cursos findByTitulo(String titulo);
}
