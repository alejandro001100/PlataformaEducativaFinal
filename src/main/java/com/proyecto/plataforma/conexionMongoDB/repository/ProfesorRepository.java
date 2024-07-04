package com.proyecto.plataforma.conexionMongoDB.repository;

import com.proyecto.plataforma.capaNegocio.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfesorRepository extends MongoRepository<Profesor, String> {
    Profesor findByCorreo(String correo);
}
//Final version

