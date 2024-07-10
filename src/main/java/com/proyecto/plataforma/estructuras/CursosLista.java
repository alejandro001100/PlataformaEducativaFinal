package com.proyecto.plataforma.estructuras;

import com.proyecto.plataforma.capaNegocio.Capitulo;
import com.proyecto.plataforma.capaNegocio.Cursos;
import com.proyecto.plataforma.capaNegocio.Profesor;
import com.proyecto.plataforma.conexionMongoDB.services.CursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Component
public class CursosLista {
    private List<Cursos> cursosLista;
    private final CursosService cursosService;

    @Autowired
    public CursosLista(CursosService cursosService) {
        this.cursosService = cursosService;
        this.cursosLista = new ArrayList<>();
        cargarCursos();
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
        cursosService.eliminar(cursos);
    }

    public void cargarCursos() {
        List<Cursos> cursos = (List<Cursos>) cursosService.encontrarTodos();
        cursosLista.clear();
        cursosLista.addAll(cursos);

        // Actualizar PriorityQueue de capítulos para cada curso
        cursosLista.forEach(curso -> {
            PriorityQueue<Capitulo> priorityQueue = new PriorityQueue<>(curso.getCapitulos());
            curso.setCapitulos(priorityQueue);
        });
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
        return cursosLista.stream()
                .filter(c -> c.getArea().equalsIgnoreCase(area))
                .collect(Collectors.toList());
    }

    public List<Cursos> buscarCursosPorTituloOArea(String filtro) {
        return cursosLista.stream()
                .filter(c -> c.getTitulo().toLowerCase().contains(filtro.toLowerCase()) ||
                        c.getArea().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Cursos> buscarCursosPorTemayProfesor(String tema, Profesor profesor) {
        return cursosService.buscarPorTemaYProfesor(tema, profesor);
    }

    public List<Cursos> buscarCursosPorTema(String tema) {
        return cursosService.buscarPorTema(tema);
    }

    public List<Cursos> buscarPorProfesor(Profesor profesor) {
        return cursosService.buscarPorProfesor(profesor);
    }

    public List<Cursos> encontrarTodos() {
        return (List<Cursos>) cursosService.encontrarTodos();
    }

    public List<Cursos> buscarPorProfesorId(String id){
        List<Cursos> lista= new ArrayList<>();
        for(Cursos c : cursosLista){
            if(c.getProfesor().getId().equals(id)){
                lista.add(c);
            }
        }
        return lista;
    }
}
