package com.proyecto.plataforma.capaNegocio;

import java.util.HashMap;
import java.util.Map;

public class Nodo {
    private String nombre;
    private Map<Nodo, Integer> vecinos;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.vecinos = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Map<Nodo, Integer> getVecinos() {
        return vecinos;
    }

    public void agregarVecino(Nodo vecino, int distancia) {
        vecinos.put(vecino, distancia);
    }
}