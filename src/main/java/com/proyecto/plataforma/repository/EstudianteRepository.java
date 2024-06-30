package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    Estudiante findByCorreo(String correo);

    // Consulta personalizada para buscar estudiantes por curso
    @Query("{ 'cursosTomados.$id': ?0 }")
    List<Estudiante> findByCursoId(String cursoId);
}
//Final version

