package com.proyecto.plataforma.estructuras;

import com.proyecto.plataforma.capaNegocio.Capitulo;
import com.proyecto.plataforma.conexionMongoDB.services.CapituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;

@Component
public class CapituloColaP {
    private PriorityQueue<Capitulo> capituloLista;
    private final CapituloService capituloService;

    @Autowired
    public CapituloColaP(CapituloService capituloService) {
        this.capituloService = capituloService;
        this.capituloLista = new PriorityQueue<>();
        cargarCapitulo();
    }

    public PriorityQueue<Capitulo> getCapituloLista() {
        return capituloLista;
    }

    public void setCapituloLista(PriorityQueue<Capitulo> capituloLista) {
        this.capituloLista = capituloLista;
    }

    public void guardarCapitulo(Capitulo capitulo) {
        capituloService.saveCapitulo(capitulo);
        cargarCapitulo();
    }

    public void eliminarCapitulo(Capitulo capitulo) {
        capituloService.delete(capitulo);
        cargarCapitulo();
    }

    public void cargarCapitulo() {
        PriorityQueue<Capitulo> capitulos = new PriorityQueue<>(capituloService.findAll());
        capituloLista.clear();
        capituloLista.addAll(capitulos);
    }

    public Capitulo buscarCapituloTitulo(String titulo) {
        for (Capitulo c : capituloLista) {
            if (c.getTitulo().equals(titulo)) {
                return c;
            }
        }
        return null;
    }

    public boolean isTituloRegistrado(String titulo) {
        for (Capitulo c : capituloLista) {
            if (c.getTitulo().equals(titulo)) {
                return true;
            }
        }
        return false;
    }
}
//Final version

