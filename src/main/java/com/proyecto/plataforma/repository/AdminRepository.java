package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByCorreo(String correo);
}
//Final version

