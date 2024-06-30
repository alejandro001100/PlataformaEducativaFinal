package com.proyecto.plataforma.repository;

import com.proyecto.plataforma.data.Cuestionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CuestionarioRepository extends MongoRepository<Cuestionario, String> {
    Cuestionario findByCapituloId(String capituloId);

    List<Cuestionario> findByTituloContaining(String titulo);
    List<Cuestionario> findByTiempoExamenBetween(int minTiempo, int maxTiempo);
}
//Final version

