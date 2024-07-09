package com.proyecto.plataforma.estructuras;

import com.proyecto.plataforma.capaNegocio.Cuestionario;
import com.proyecto.plataforma.capaNegocio.Profesor;
import com.proyecto.plataforma.conexionMongoDB.services.CuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CuestionarioLista {
    private List<Cuestionario> cuestionarioLista;
    private final CuestionarioService cuestionarioService;

    @Autowired
    public CuestionarioLista(CuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
        this.cuestionarioLista = new ArrayList<>();
        cargarCuestionarios();
    }

    public List<Cuestionario> getCuestionarioLista() {
        return cuestionarioLista;
    }

    public void setCuestionarioLista(List<Cuestionario> cuestionarioLista) {
        this.cuestionarioLista = cuestionarioLista;
    }

    public void guardarCuestionario(Cuestionario cuestionario) {
        cuestionarioService.saveCuestionario(cuestionario);
        cargarCuestionarios();
    }

    public void eliminarCuestionario(Cuestionario cuestionario) {
        cuestionarioService.delete(cuestionario);
        cargarCuestionarios();
    }

    public void cargarCuestionarios() {
        List<Cuestionario> cuestionarios = cuestionarioService.findAll();
        cuestionarioLista.clear();
        cuestionarioLista.addAll(cuestionarios);
    }

    public Cuestionario buscarCuestionarioTitulo(String titulo) {
        for (Cuestionario c : cuestionarioLista) {
            if (c.getTitulo().equals(titulo)) {
                return c;
            }
        }
        return null;
    }

    public boolean isTituloRegistrado(String titulo) {
        for (Cuestionario c : cuestionarioLista) {
            if (c.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }

    public List<Cuestionario> buscarPorAutor(String autor) {
        return cuestionarioService.buscarPorAutor(autor);
    }
}

