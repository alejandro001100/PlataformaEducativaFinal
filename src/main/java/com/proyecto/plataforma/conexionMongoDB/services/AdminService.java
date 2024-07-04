package com.proyecto.plataforma.conexionMongoDB.services;

import com.proyecto.plataforma.capaNegocio.Admin;
import com.proyecto.plataforma.conexionMongoDB.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {


    @Autowired
    private AdminRepository adminRepository;
    public Admin saveAdmin(Admin admin) {
        if (admin.getId() == null || admin.getId().isEmpty()) {
            admin.setId( UUID.randomUUID().toString());
        }
        return adminRepository.save(admin);
    }

    public Admin findByCorreo(String correo) {
        return adminRepository.findByCorreo(correo);
    }

    public boolean isCorreoRegistrado(String correo) {
        return adminRepository.findByCorreo(correo) != null;
    }


    public Iterable<Admin> findAll() {
        return adminRepository.findAll();
    }
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public void delete(Admin admin) {
        adminRepository.delete(admin);
    }


}
//Final version

