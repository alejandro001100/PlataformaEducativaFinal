package com.proyecto.plataforma.services;

import com.proyecto.plataforma.data.Admin;
import com.proyecto.plataforma.data.Estudiante;
import com.proyecto.plataforma.data.User;
import com.proyecto.plataforma.repository.AdminRepository;
import com.proyecto.plataforma.repository.EstudianteRepository;
import com.proyecto.plataforma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;

@Service
public class AdminService {


    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin) {
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
