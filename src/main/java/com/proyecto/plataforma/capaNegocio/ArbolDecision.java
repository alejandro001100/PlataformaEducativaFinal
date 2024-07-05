package com.proyecto.plataforma.capaNegocio;
import java.util.ArrayList;
import java.util.List;

public class ArbolDecision {

    private Nodo raiz;

    public ArbolDecision() {
        this.raiz = new Nodo("¿Te interesa el reciclaje?");
        Nodo nodo1 = new Nodo("¿Te gustaría aprender a reciclar en casa?");
        Nodo nodo2 = new Nodo("¿Te interesa conocer las leyes de reciclaje?");
        Nodo nodo3 = new Nodo("¿Quieres saber sobre el impacto ambiental del reciclaje?");

        raiz.agregarNodoSi(nodo1);
        raiz.agregarNodoNo(nodo2);
        nodo1.agregarNodoSi(new Nodo("Reciclaje desde casa", true));
        nodo1.agregarNodoNo(nodo3);
        nodo2.agregarNodoSi(new Nodo("Leyes en cuanto al reciclaje", true));
        nodo2.agregarNodoNo(new Nodo("Impacto ambiental del reciclaje", true));
        nodo3.agregarNodoSi(new Nodo("Impacto ambiental del reciclaje", true));
        nodo3.agregarNodoNo(new Nodo("Reciclaje desde casa", true));
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public static class Nodo {
        private String preguntaOArea;
        private Nodo nodoSi;
        private Nodo nodoNo;
        private boolean esHoja;

        public Nodo(String preguntaOArea) {
            this.preguntaOArea = preguntaOArea;
            this.esHoja = false;
        }

        public Nodo(String preguntaOArea, boolean esHoja) {
            this.preguntaOArea = preguntaOArea;
            this.esHoja = esHoja;
        }

        public String getPreguntaOArea() {
            return preguntaOArea;
        }

        public Nodo getNodoSi() {
            return nodoSi;
        }

        public void agregarNodoSi(Nodo nodoSi) {
            this.nodoSi = nodoSi;
        }

        public Nodo getNodoNo() {
            return nodoNo;
        }

        public void agregarNodoNo(Nodo nodoNo) {
            this.nodoNo = nodoNo;
        }

        public boolean esHoja() {
            return esHoja;
        }
    }
}
