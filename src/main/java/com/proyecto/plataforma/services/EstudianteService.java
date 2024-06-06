package com.proyecto.plataforma.services;

import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.data.User;
import com.proyecto.plataforma.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante saveEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante findByCorreo(String correo) {
        return estudianteRepository.findByCorreo(correo);
    }

    public boolean isCorreoRegistrado(String correo) {
        return estudianteRepository.findByCorreo(correo) != null;
    }


    public Iterable<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }
   /* public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }*/

    public void delete(Estudiante estudiante) {
        estudianteRepository.delete(estudiante);
    }

}
