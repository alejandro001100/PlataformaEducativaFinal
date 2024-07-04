package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "notas_cuestionario")
public class NotaCuestionario {
    @Id
    private String id;
    private String tituloCuestionario;
    private int nota;
    private Integer intentos;  // Cambiamos a Integer para permitir nulos

    public NotaCuestionario(String tituloCuestionario, int nota, Integer intentos) {
        this.tituloCuestionario = tituloCuestionario;
        this.nota = nota;
        this.intentos = intentos != null ? intentos : 0;  // Valor predeterminado de 0 si es nulo
    }

    public NotaCuestionario() {
        // Constructor sin argumentos para JPA
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTituloCuestionario() {
        return tituloCuestionario;
    }

    public void setTituloCuestionario(String tituloCuestionario) {
        this.tituloCuestionario = tituloCuestionario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }
}
//Final version

