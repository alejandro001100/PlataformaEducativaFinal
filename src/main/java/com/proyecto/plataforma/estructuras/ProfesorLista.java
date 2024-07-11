package com.proyecto.plataforma.estructuras;

import com.proyecto.plataforma.capaNegocio.Profesor;
import com.proyecto.plataforma.conexionMongoDB.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfesorLista {

    //Atributo Lista
    private List<Profesor> profesorLista;
    //Atributo que conecta con MongoDB
    private final ProfesorService profesorService;

    //Constructor
    @Autowired
    public ProfesorLista(ProfesorService profesorService) {
        this.profesorService = profesorService;
        this.profesorLista = new ArrayList<>();
        cargarProfesor(); // Cargar profesores al inicializar
    }

    //Getter y Setters
    public List<Profesor> getProfesorLista() {
        return profesorLista;
    }

    public void setProfesorLista(List<Profesor> profesorLista) {
        this.profesorLista = profesorLista;
    }

    //Metodos
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
        profesorLista.clear();
        profesorLista.addAll(profesores);
    }

    public Profesor buscarProfesorCorreo(String correo) {
        for (Profesor p : profesorLista) {
            if (p.getCorreo().equals(correo)) {
                return p;
            }
        }
        return null;
    }

    public boolean isCorreoRegistrado(String correo) {
        for (Profesor p : profesorLista) {
            if (p.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public List<Profesor> getAllProfesores() {
        return profesorLista;
    }
}
//Final version

