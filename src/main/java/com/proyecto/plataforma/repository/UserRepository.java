package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByCorreo(String correo);
}
