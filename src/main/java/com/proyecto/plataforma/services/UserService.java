package com.proyecto.plataforma.services;

import com.proyecto.plataforma.data.User;
import com.proyecto.plataforma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByCorreo(String correo) {
        return userRepository.findByCorreo(correo);
    }

    public boolean isCorreoRegistrado(String correo) {
        return userRepository.findByCorreo(correo) != null;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}

//Final version

