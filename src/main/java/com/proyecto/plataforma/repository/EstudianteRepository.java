package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    Estudiante findByCorreo(String correo);
}
