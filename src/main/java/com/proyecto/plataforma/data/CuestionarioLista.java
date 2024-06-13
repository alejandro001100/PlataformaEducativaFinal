package com.proyecto.plataforma.data;

import com.proyecto.plataforma.services.CuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CuestionarioLista {
    List<Cursos> cuestionarioLista;
    private final CuestionarioService cuestionarioService;
    @Autowired
    public CursosLista(CuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
        cuestionarioLista = new ArrayList<>();
    }

    public List<Cursos> getCuestionarioLista() {
        return cuestionarioLista;
    }

    public void setCuestionarioLista(List<Cuestionario> cuestionarioLista) {
        this.cuestionarioLista = cuestionarioLista;
    }

    public void guardarCuestionario(Cuestionario cuestionario) {
        cuestionarioLista.add(cuestionario);
        cuestionarioService.saveCuestionario(cuestionario);
    }

    public void eliminarCuestionario(Cuestionario cuestionario) {
        cuestionarioLista.remove(cuestionario);
        cuestionarioService.delete(cuestionario);
    }

    public void cargarCuestionario() {
        List<Cuestionario> cuestionario = (List<Cuestionario>) cuestionarioService.findAll();
        cuestionarioLista.addAll(cuestionario);
    }

    public Cuestionario buscarCuestionarioTitulo(String titulo) {
        for (Cuestionario cu : cuestionarioLista) {
            if (cu.getTitulo().equals(titulo)) {
                return cu;
            }
        }
        return null;
    }

    public boolean isTituloRegistradoCuestionario(String titulo) {
        for (Cuestionario cu : cuestionarioLista) {
            if (cu.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }

    public List<Cuestionario> getCuestionarioPorArea(String area) {
        List<Cuestionario> cuestionarioPorArea = new ArrayList<>();
        for (Cuestionario cu : cuestionarioLista) {
            if (cu.getArea().equals(area)) {
                cuestionarioPorArea.add(cu);
            }
        }
        return cuestionarioPorArea;
    }




}
