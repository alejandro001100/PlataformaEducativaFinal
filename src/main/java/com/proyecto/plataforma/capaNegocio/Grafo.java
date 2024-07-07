package com.proyecto.plataforma.capaNegocio;

import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private Map<String, Nodo> nodos;

    public Grafo() {
        nodos = new HashMap<>();
    }

    public void agregarNodo(String nombre) {
        nodos.put(nombre, new Nodo(nombre));
    }

    public Nodo obtenerNodo(String nombre) {
        return nodos.get(nombre);
    }

    public void agregarArista(String nombre1, String nombre2, int distancia) {
        Nodo nodo1 = nodos.get(nombre1);
        Nodo nodo2 = nodos.get(nombre2);
        nodo1.agregarVecino(nodo2, distancia);
        nodo2.agregarVecino(nodo1, distancia); // Suponiendo un grafo no dirigido
    }

    public Map<String, Nodo> getNodos() {
        return nodos;
    }
}