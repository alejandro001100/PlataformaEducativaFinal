package com.proyecto.plataforma.data;

import com.proyecto.plataforma.services.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminLista {

    List<Admin> adminLista;
    private final AdminService adminService;

    @Autowired
    public AdminLista(AdminService adminService) {
        this.adminService = adminService;
        adminLista = new ArrayList<>();
    }

    public List<Admin> getAdminLista() {
        return adminLista;
    }

    public void setAdminLista(List<Admin> adminLista) {
        this.adminLista = adminLista;
    }

    public void agregarAdmin(Admin admin) {
        adminLista.add(admin);
        adminService.saveAdmin(admin);
    }

    public void eliminarAdmin(Admin admin) {
        adminLista.remove(admin);
        adminService.delete(admin);
    }

    public void cargarAdmin() {
        List<Admin> admin = (List<Admin>) adminService.findAll();
        adminLista.addAll(admin);
    }

    public Admin buscarAdminCorreo(String correo) {
        for (Admin e : adminLista) {
            if (e.getCorreo().equals(correo)) {
                return e;
            }
        }
        return null;
    }

    public boolean isCorreoRegistrado(String correo) {
        for (Admin e : adminLista) {
            if (e.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public List<Admin> getAllAdmin() {
        return adminLista;
    }
}
