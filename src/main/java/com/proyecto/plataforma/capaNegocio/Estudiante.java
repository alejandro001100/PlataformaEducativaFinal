package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
//usuario tipo estudiante, hereda de User

@Document(collection = "estudiantes")
public class Estudiante extends User {

    //Atributos
    @Id
    private String id;

    @DBRef
    private List<Cursos> cursosTomados;

    private List<NotaCuestionario> notasCuestionarios;
    private List<Cuestionario> cuestionariosRendidos;

    //Constructores
    public Estudiante(String nombre, String apellido, String correo, String contraseña, String rol, String id) {
        super(nombre, apellido, correo, contraseña, rol);
        this.id = id;
        this.cursosTomados = new ArrayList<>();
        this.notasCuestionarios = new ArrayList<>();
        this.cuestionariosRendidos = new ArrayList<>();
    }

    public Estudiante() {
        super();
        this.cursosTomados = new ArrayList<>();
        this.notasCuestionarios = new ArrayList<>();
    }


    //Getters y Setters
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


    public List<NotaCuestionario> getNotasCuestionarios() {
        return notasCuestionarios;
    }

    public void setNotasCuestionarios(List<NotaCuestionario> notasCuestionarios) {
        this.notasCuestionarios = notasCuestionarios;
    }

    //Metodos

    public void agregarNotaCuestionario(NotaCuestionario nota) {
        this.notasCuestionarios.add(nota);
    }


   public NotaCuestionario getNotaCuestionario(String cuestionarioId) {
       return notasCuestionarios.stream()
               .filter(nota -> {
                   String tituloCuestionario = nota.getTituloCuestionario();
                   return tituloCuestionario != null && tituloCuestionario.equals(cuestionarioId);
               })
               .findFirst()
               .orElse(null);
   }


    public Cuestionario verCuestionario(String cuestionarioId) {
        for (Cuestionario cuestionario : cuestionariosRendidos) {
            if (cuestionario.getId().equals(cuestionarioId)) {
                return cuestionario;
            }
        }
        return null;
    }




    public Boolean isCursoTomado(String cursoT) {
       for (Cursos c : cursosTomados) {
           if (c.getTitulo().equals(cursoT)) {
               return true;
           }
       }
       return false;
   }
    public void agregarCursoTomado(Cursos curso) {
        this.cursosTomados.add(curso);
    }

    public void eliminarCursoTomado(Cursos curso) {
        this.cursosTomados.remove(curso);
    }

}
