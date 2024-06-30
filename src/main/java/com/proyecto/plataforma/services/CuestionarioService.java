package com.proyecto.plataforma.services;

import com.proyecto.plataforma.data.Cuestionario;
import com.proyecto.plataforma.repository.CuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuestionarioService {
    @Autowired
    private CuestionarioRepository cuestionarioRepository;

    public List<Cuestionario> findAll() {
        return cuestionarioRepository.findAll();
    }

    public Cuestionario saveCuestionario(Cuestionario cuestionario) {
        return cuestionarioRepository.save(cuestionario);
    }

    public void delete(Cuestionario cuestionario) {
        cuestionarioRepository.delete(cuestionario);
    }

    public Optional<Cuestionario> findById(String id) {
        return cuestionarioRepository.findById(id);
    }

    public Optional<Cuestionario> buscarPorCapituloId(String capituloId) {
        return Optional.ofNullable(cuestionarioRepository.findByCapituloId(capituloId));
    }

    public List<Cuestionario> buscarPorTitulo(String titulo) {
        return cuestionarioRepository.findByTituloContaining(titulo);
    }

    public Cuestionario actualizarCuestionario(Cuestionario cuestionario) {
        Optional<Cuestionario> existenteOpt = findById(cuestionario.getId());
        if (existenteOpt.isPresent()) {
            Cuestionario existente = existenteOpt.get();
            existente.setTitulo(cuestionario.getTitulo());
            existente.setPreguntas(cuestionario.getPreguntas());
            existente.setPuntajeTotal(cuestionario.getPuntajeTotal());
            existente.setTiempoExamen(cuestionario.getTiempoExamen());
            existente.setIntentos(cuestionario.getIntentos());
            return saveCuestionario(existente);
        } else {
            return null;
        }
    }

    public List<Cuestionario> buscarPorTiempoExamen(int minTiempo, int maxTiempo) {
        return cuestionarioRepository.findByTiempoExamenBetween(minTiempo, maxTiempo);
    }
}
//Final version

