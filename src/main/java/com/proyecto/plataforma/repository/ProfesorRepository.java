package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfesorRepository extends MongoRepository<Profesor, String> {
    Profesor findByCorreo(String correo);
}
