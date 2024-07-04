package com.proyecto.plataforma.conexionMongoDB.repository;

import com.proyecto.plataforma.capaNegocio.Cuestionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CuestionarioRepository extends MongoRepository<Cuestionario, String> {
    Cuestionario findByCapituloId(String capituloId);

    List<Cuestionario> findByTituloContaining(String titulo);
    List<Cuestionario> findByTiempoExamenBetween(int minTiempo, int maxTiempo);
}
//Final version

