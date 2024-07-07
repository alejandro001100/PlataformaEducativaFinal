package com.proyecto.plataforma.views.minijuegos;
import com.proyecto.plataforma.capaNegocio.AlgoritmoDijkstra;
import com.proyecto.plataforma.capaNegocio.Grafo;
import com.proyecto.plataforma.capaNegocio.Nodo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Route("juego")
public class JuegoView extends VerticalLayout {

    private static final List<String> sectores = Arrays.asList("Quito", "Quitumbe", "Valle", "Tumbaco");

    public JuegoView() {
        Button jugarButton = new Button("Iniciar Juego", e -> iniciarJuego());
        add(jugarButton);
    }

    private void iniciarJuego() {
        Grafo grafo = new Grafo();

        // Agregar nodos (sectores y estaciones de reciclaje)
        grafo.agregarNodo("Quito");
        grafo.agregarNodo("Quitumbe");
        grafo.agregarNodo("Valle");
        grafo.agregarNodo("Tumbaco");
        grafo.agregarNodo("Estacion1");
        grafo.agregarNodo("Estacion2");
        grafo.agregarNodo("Estacion3");

        // Agregar aristas (conexiones entre nodos)
        grafo.agregarArista("Quito", "Quitumbe", 10);
        grafo.agregarArista("Quito", "Valle", 20);
        grafo.agregarArista("Quitumbe", "Valle", 5);
        grafo.agregarArista("Quitumbe", "Tumbaco", 15);
        grafo.agregarArista("Valle", "Tumbaco", 10);
        grafo.agregarArista("Quito", "Estacion1", 8);
        grafo.agregarArista("Quitumbe", "Estacion2", 12);
        grafo.agregarArista("Valle", "Estacion3", 25);
        grafo.agregarArista("Tumbaco", "Estacion1", 18);
        grafo.agregarArista("Tumbaco", "Estacion2", 10);

        // Seleccionar un sector inicial aleatorio
        Random random = new Random();
        String sectorInicial = sectores.get(random.nextInt(sectores.size()));
        Nodo nodoInicial = grafo.obtenerNodo(sectorInicial);

        // Encontrar la estación de reciclaje más cercana
        Map<Nodo, Integer> distancias = AlgoritmoDijkstra.encontrarRutaMasCorta(grafo, nodoInicial);

        Nodo estacionMasCercana = null;
        int distanciaMinima = Integer.MAX_VALUE;
        for (Map.Entry<Nodo, Integer> entrada : distancias.entrySet()) {
            if (entrada.getKey().getNombre().startsWith("Estacion") && entrada.getValue() < distanciaMinima) {
                distanciaMinima = entrada.getValue();
                estacionMasCercana = entrada.getKey();
            }
        }

        if (estacionMasCercana != null) {
            Notification.show("La estación de reciclaje más cercana es: " + estacionMasCercana.getNombre() + " a una distancia de " + distanciaMinima + " unidades.");
        } else {
            Notification.show("No se encontró ninguna estación de reciclaje.");
        }
    }
}