package com.proyecto.plataforma.conexionMongoDB.repository;

import com.proyecto.plataforma.capaNegocio.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByCorreo(String correo);
}
//Final version

