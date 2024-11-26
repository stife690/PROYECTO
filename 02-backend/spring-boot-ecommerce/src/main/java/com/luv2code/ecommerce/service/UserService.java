package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona las operaciones relacionadas con los usuarios en el sistema.
 * Proporciona métodos para registrar un nuevo usuario, buscar usuarios por correo electrónico y verificar si un correo ya está registrado.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @param name El nombre completo del usuario.
     * @param role El rol asignado al usuario (por ejemplo, "ADMIN", "USER").
     * @return El usuario recién registrado.
     * @throws RuntimeException Si el correo electrónico ya está registrado en el sistema.
     */
    public User registerUser(String email, String password, String name, String role) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Este correo ya está registrado.");
        }
        // Crear nuevo usuario
        User newUser = new User(email, passwordEncoder.encode(password), name, role);
        return userRepository.save(newUser);
    }

    /**
     * Busca un usuario por su correo electrónico.
     * 
     * @param email El correo electrónico del usuario.
     * @return El usuario encontrado, o null si no se encuentra un usuario con ese correo.
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Verifica si un correo electrónico ya está registrado en el sistema.
     * 
     * @param email El correo electrónico a verificar.
     * @return true si el correo electrónico está registrado, false si no lo está.
     */
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email) != null; // Devuelve true si el correo ya existe
    }
}
