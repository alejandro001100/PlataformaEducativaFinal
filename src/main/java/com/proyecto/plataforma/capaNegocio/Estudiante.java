package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "estudiantes")
public class Estudiante extends User {
    @Id
    private String id;

    @DBRef
    private List<Cursos> cursosTomados;

    private List<NotaCuestionario> notasCuestionarios;

    public Estudiante(String nombre, String apellido, String correo, String contraseña, String rol, String id) {
        super(nombre, apellido, correo, contraseña, rol);
        this.id = id;
        this.cursosTomados = new ArrayList<>();
        this.notasCuestionarios = new ArrayList<>();
    }

    public Estudiante() {
        super();
        this.cursosTomados = new ArrayList<>();
        this.notasCuestionarios = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Cursos> getCursosTomados() {
        return cursosTomados;
    }

    public void setCursosTomados(List<Cursos> cursosTomados) {
        this.cursosTomados = cursosTomados;
    }

    public void agregarCursoTomado(Cursos curso) {
        this.cursosTomados.add(curso);
    }

    public void eliminarCursoTomado(Cursos curso) {
        this.cursosTomados.remove(curso);
    }

    public List<NotaCuestionario> getNotasCuestionarios() {
        return notasCuestionarios;
    }

    public void setNotasCuestionarios(List<NotaCuestionario> notasCuestionarios) {
        this.notasCuestionarios = notasCuestionarios;
    }

    public void agregarNotaCuestionario(NotaCuestionario nota) {
        this.notasCuestionarios.add(nota);
    }

   /* public NotaCuestionario getNotaCuestionario(String cuestionarioId) {
        return notasCuestionarios.stream()
                .filter(nota -> nota.getTituloCuestionario().equals(cuestionarioId))
                .findFirst()
                .orElse(null);
    }*/
   public NotaCuestionario getNotaCuestionario(String cuestionarioId) {
       return notasCuestionarios.stream()
               .filter(nota -> {
                   String tituloCuestionario = nota.getTituloCuestionario();
                   return tituloCuestionario != null && tituloCuestionario.equals(cuestionarioId);
               })
               .findFirst()
               .orElse(null);
   }

   public Boolean isCursoTomado(String cursoT) {
       for (Cursos c : cursosTomados) {
           if (c.getTitulo().equals(cursoT)) {
               return true;
           }
       }
       return false;
   }


}
