package com.proyecto.plataforma.capaNegocio;

import java.util.*;

public class AlgoritmoDijkstra {
    public static Map<Nodo, Integer> encontrarRutaMasCorta(Grafo grafo, Nodo inicio) {
        Map<Nodo, Integer> distancias = new HashMap<>();
        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));
        Map<Nodo, Nodo> predecesores = new HashMap<>();

        for (Nodo nodo : grafo.getNodos().values()) {
            if (nodo.equals(inicio)) {
                distancias.put(nodo, 0);
            } else {
                distancias.put(nodo, Integer.MAX_VALUE);
            }
            cola.add(nodo);
        }

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            for (Map.Entry<Nodo, Integer> vecino : actual.getVecinos().entrySet()) {
                Nodo vecinoNodo = vecino.getKey();
                int peso = vecino.getValue();

                int distanciaAlternativa = distancias.get(actual) + peso;
                if (distanciaAlternativa < distancias.get(vecinoNodo)) {
                    distancias.put(vecinoNodo, distanciaAlternativa);
                    predecesores.put(vecinoNodo, actual);

                    cola.remove(vecinoNodo);
                    cola.add(vecinoNodo);
                }
            }
        }

        return distancias;
    }
}