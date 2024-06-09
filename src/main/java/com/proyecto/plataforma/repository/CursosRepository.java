package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Admin;
import com.proyecto.plataforma.data.Cursos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CursosRepository extends MongoRepository<Cursos, String>{

        Cursos findByTitulo(String titulo);


}
