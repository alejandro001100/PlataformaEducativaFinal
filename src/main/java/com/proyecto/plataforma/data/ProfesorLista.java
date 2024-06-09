package com.proyecto.plataforma.data;

import com.proyecto.plataforma.services.EstudianteService;
import com.proyecto.plataforma.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfesorLista {
    List<Profesor> profesorLista;
    private final ProfesorService profesorService;

    @Autowired
    public ProfesorLista(ProfesorService profesorService) {
        this.profesorService = profesorService;
        profesorLista = new ArrayList<>();
    }

    public List<Profesor> getProfesorLista() {
        return profesorLista;
    }

    public void setProfesorLista(List<Profesor> profesorLista) {
        this.profesorLista = profesorLista;
    }

    public void agregarProfesor(Profesor profesor) {
        profesorLista.add(profesor);
        profesorService.saveProfesor(profesor);
    }

    public void eliminarProfesor(Profesor profesor) {
        profesorLista.remove(profesor);
        profesorService.delete(profesor);
    }

    public void cargarProfesor() {
        List<Profesor> profesores = (List<Profesor>) profesorService.findAll();
        profesorLista.addAll(profesores);
    }

    public Profesor buscarProfesorCorreo(String correo) {
        for (Profesor e : profesorLista) {
            if (e.getCorreo().equals(correo)) {
                return e;
            }
        }
        return null;
    }
    public boolean isCorreoRegistrado(String correo) {
        for (Profesor e : profesorLista) {
            if (e.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public List<Profesor> getAllProfesores() {
        return profesorLista;
    }
}
