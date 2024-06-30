package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Capitulo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapituloRepository extends MongoRepository<Capitulo, String> {
}
//Final version

