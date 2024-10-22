package com.proyecto.plataforma.capaNegocio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Clase principal, de este heredan profesor, estudiante y administrador.
public class User {

    //Atributos
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String rol;



//Constructores
    public User(String nombre, String apellido, String correo, String contraseña, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public User() {

    }

    //Getters y Setters
    public String getNombre ( ) {
        return nombre;
    }

    public void setNombre ( String nombre ) {
        this.nombre = nombre;
    }

    public String getApellido ( ) {
        return apellido;
    }

    public void setApellido ( String apellido ) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
//Final version

