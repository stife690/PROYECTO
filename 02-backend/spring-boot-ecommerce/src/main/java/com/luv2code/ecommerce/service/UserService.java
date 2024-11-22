package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrar un nuevo usuario
    public User registerUser(String email, String password, String name, String role) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Este correo ya está registrado.");
        }
        // Crear nuevo usuario
        User newUser = new User(email, passwordEncoder.encode(password), name, role);
        return userRepository.save(newUser);
    }

    // Buscar un usuario por email
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Verificar si un correo ya está registrado
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email) != null; // Devuelve true si el correo ya existe
    }
    
}