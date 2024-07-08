package com.proyecto.plataforma.capaNegocio;
import java.util.HashMap;
import java.util.Map;

public class Nodo {
    private String nombre;
    private Map<Nodo, Integer> vecinos;
    private Nodo padre; // Nuevo atributo para mantener el nodo padre

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.vecinos = new HashMap<>();
        this.padre = null; // Inicialmente el nodo padre es nulo
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

    // Métodos para establecer y obtener el nodo padre
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getPadre() {
        return padre;
    }
}
