package com.proyecto.plataforma.views.minijuegos;

import com.proyecto.plataforma.capaNegocio.AlgoritmoDijkstra;
import com.proyecto.plataforma.capaNegocio.Grafo;
import com.proyecto.plataforma.capaNegocio.Nodo;
import com.proyecto.plataforma.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Route(value = "juego", layout = MainLayout.class)
@PageTitle("Árbol de Decisión | Plataforma Educativa")
public class JuegoView extends VerticalLayout {

    private static final List<String> sectores = Arrays.asList("Quito", "Quitumbe", "Valle", "Tumbaco");

    private ComboBox<String> sectorComboBox;
    private Button jugarButton;
    private TextArea resultadoTextArea;

    public JuegoView() {
        sectorComboBox = new ComboBox<>("Selecciona tu sector:");
        sectorComboBox.setItems(sectores);
        sectorComboBox.setWidth("300px"); // Ancho del ComboBox

        jugarButton = new Button("Iniciar Juego", e -> iniciarJuego());
        jugarButton.setWidth("200px"); // Ancho del Button

        resultadoTextArea = new TextArea("Resultado:");
        resultadoTextArea.setReadOnly(true);
        resultadoTextArea.setWidth("400px"); // Ancho del TextArea
        resultadoTextArea.setHeight("150px"); // Altura del TextArea

        add(sectorComboBox, jugarButton, resultadoTextArea);
    }

    private void iniciarJuego() {
        String sectorSeleccionado = sectorComboBox.getValue();
        if (sectorSeleccionado == null) {
            resultadoTextArea.setValue("Por favor selecciona un sector antes de iniciar el juego.");
            return;
        }

        Grafo grafo = new Grafo();

        grafo.agregarNodo("Quito");
        grafo.agregarNodo("Quitumbe");
        grafo.agregarNodo("Valle");
        grafo.agregarNodo("Tumbaco");
        grafo.agregarNodo("Estacion1");
        grafo.agregarNodo("Estacion2");
        grafo.agregarNodo("Estacion3");

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

        Nodo nodoInicial = grafo.obtenerNodo(sectorSeleccionado);

        Map<Nodo, Integer> distancias = AlgoritmoDijkstra.encontrarRutaMasCorta(grafo, nodoInicial);

        Nodo estacionMasCercana = null;
        int distanciaMinima = Integer.MAX_VALUE;
        Stack<Nodo> caminoMinimo = new Stack<>();
        for (Map.Entry<Nodo, Integer> entrada : distancias.entrySet()) {
            if (entrada.getKey().getNombre().startsWith("Estacion") && entrada.getValue() < distanciaMinima) {
                distanciaMinima = entrada.getValue();
                estacionMasCercana = entrada.getKey();
                // Obtener el camino mínimo
                Nodo actual = entrada.getKey();
                caminoMinimo.push(actual);
                while (actual.getPadre() != null) {
                    caminoMinimo.push(actual.getPadre());
                    actual = actual.getPadre();
                }
            }
        }

        if (estacionMasCercana != null) {
            resultadoTextArea.setValue("La estación de reciclaje más cercana para el sector " + sectorSeleccionado + " es: " +
                    estacionMasCercana.getNombre() + " a una distancia de " + distanciaMinima + " unidades.");
        } else {
            resultadoTextArea.setValue("No se encontró ninguna estación de reciclaje cercana para el sector " + sectorSeleccionado);
        }
    }
}
