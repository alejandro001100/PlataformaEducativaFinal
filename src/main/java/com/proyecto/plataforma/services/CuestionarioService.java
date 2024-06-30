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

    public Cuestionario findById(String id) {
        return cuestionarioRepository.findById(id).orElse(null);
    }
}

