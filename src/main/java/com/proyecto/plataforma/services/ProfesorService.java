package com.proyecto.plataforma.services;

import com.proyecto.plataforma.data.Profesor;
import com.proyecto.plataforma.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    public Profesor saveProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public Profesor findByCorreo(String correo) {
        return profesorRepository.findByCorreo(correo);
    }

    public boolean isCorreoRegistrado(String correo) {
        return profesorRepository.findByCorreo(correo) != null;
    }

    public Iterable<Profesor> findAll() {
        return profesorRepository.findAll();
    }

    public void delete(Profesor profesor) {
        profesorRepository.delete(profesor);
    }

}
