package com.proyecto.plataforma.services;

import com.proyecto.plataforma.data.Capitulo;
import com.proyecto.plataforma.repository.CapituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapituloService {
    @Autowired
    private CapituloRepository capituloRepository;

    public List<Capitulo> findAll() {
        return capituloRepository.findAll();
    }

    public Capitulo saveCapitulo(Capitulo capitulo) {
        return capituloRepository.save(capitulo);
    }

    public void delete(Capitulo capitulo) {
        capituloRepository.delete(capitulo);
    }

    public Capitulo findById(String id) {
        return capituloRepository.findById(id).orElse(null);
    }
}
//Final version

