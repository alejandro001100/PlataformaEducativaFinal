package com.proyecto.plataforma.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
@Document(collection = "estudiantes")
public class Estudiante extends User{
    @Id
    private String id;

    public Estudiante(String nombre, String apellido, String correo, String contraseña, String rol, String id) {
        super(nombre, apellido, correo, contraseña, rol);

        this.id = id;
    }

    public Estudiante() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
//Final version

