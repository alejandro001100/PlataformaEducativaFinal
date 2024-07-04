package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Admin")
public class Admin extends User {
    @Id
    private String id;

    public Admin(String nombre, String apellido, String correo, String contraseña, String rol, String id) {
        super(nombre, apellido, correo, contraseña, rol);

        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
//Final version

