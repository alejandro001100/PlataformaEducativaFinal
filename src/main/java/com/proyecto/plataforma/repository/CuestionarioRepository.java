package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Cuestionario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CuestionarioRepository extends MongoRepository<Cuestionario, String> {
    Cuestionario findByTitulo(String titulo);
}
//Final version

