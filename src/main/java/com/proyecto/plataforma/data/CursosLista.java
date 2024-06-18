package com.proyecto.plataforma.data;

import com.proyecto.plataforma.services.CursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursosLista {
    private List<Cursos> cursosLista;
    private final CursosService cursosService;

    @Autowired
    public CursosLista(CursosService cursosService) {
        this.cursosService = cursosService;
        cursosLista = new ArrayList<>();
    }

    public List<Cursos> getCursosLista() {
        return cursosLista;
    }

    public void setCursosLista(List<Cursos> cursosLista) {
        this.cursosLista = cursosLista;
    }

    public void guardarCursos(Cursos cursos) {
        if (!cursosLista.contains(cursos)) {
            cursosLista.add(cursos);
        }
        cursosService.saveCursos(cursos);
    }

    public void eliminarCursos(Cursos cursos) {
        cursosLista.remove(cursos);
        cursosService.delete(cursos);
    }

    public void cargarCursos() {
        List<Cursos> cursos = (List<Cursos>) cursosService.findAll();
        cursosLista.clear();
        cursosLista.addAll(cursos);
    }

    public Cursos buscarCursosTitulo(String titulo) {
        for (Cursos c : cursosLista) {
            if (c.getTitulo().equals(titulo)) {
                return c;
            }
        }
        return null;
    }

    public boolean isTituloRegistrado(String titulo) {
        for (Cursos c : cursosLista) {
            if (c.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }

    public List<Cursos> getCursosPorArea(String area) {
        List<Cursos> cursosPorArea = new ArrayList<>();
        for (Cursos c : cursosLista) {
            if (c.getArea().equals(area)) {
                cursosPorArea.add(c);
            }
        }
        return cursosPorArea;
    }
}
