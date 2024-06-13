package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Pregunta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PreguntaRepository extends MongoRepository<Pregunta, String> {}
