package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Admin")

//Usuario tipo Admin, hereda de user
public class Admin extends User {
   //Atributo
    @Id
    private String id;

    //Constructor
    public Admin(String nombre, String apellido, String correo, String contraseña, String rol, String id) {
        super(nombre, apellido, correo, contraseña, rol);

        this.id = id;
    }

    //Getter y setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
//Final version

