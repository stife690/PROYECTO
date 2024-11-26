package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la gestión de usuarios.
 * Proporciona endpoints para registrar, iniciar sesión, cerrar sesión y actualizar usuarios.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param user Objeto que contiene los datos del usuario.
     * @return Respuesta HTTP con el usuario guardado o un mensaje de error si el email ya está registrado.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }

        // Encriptar la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Asignar rol por defecto si no se proporciona
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        // Guardar usuario
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * Inicia sesión para un usuario existente.
     *
     * @param loginRequest Objeto que contiene las credenciales del usuario.
     * @param session Sesión HTTP para almacenar información del usuario autenticado.
     * @return Respuesta HTTP con los datos del usuario o un mensaje de error si las credenciales son incorrectas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest, HttpSession session) {
        try {
            User user = userService.findUserByEmail(loginRequest.getEmail());
            if (user == null) {
                return ResponseEntity.status(404).body("Usuario no encontrado.");
            }
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body("Credenciales incorrectas.");
            }

            session.setAttribute("user", user); // Guarda usuario en la sesión

            Map<String, Object> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
            response.put("name", user.getName());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor.");
        }
    }

    /**
     * Cierra la sesión del usuario actual.
     *
     * @param session Sesión HTTP que será invalidada.
     * @return Respuesta HTTP indicando que la sesión se cerró correctamente.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Sesión cerrada correctamente.");
    }

    /**
     * Obtiene el rol del usuario actualmente autenticado.
     *
     * @param session Sesión HTTP para obtener información del usuario.
     * @return Respuesta HTTP con el rol del usuario o "GUEST" si no hay usuario autenticado.
     */
    @GetMapping("/user-role")
    public ResponseEntity<?> getUserRole(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return ResponseEntity.ok(role != null ? role : "GUEST");
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param id ID del usuario que se va a actualizar.
     * @param updatedUser Objeto con los datos actualizados del usuario.
     * @return Respuesta HTTP con el usuario actualizado o un mensaje de error si no se encuentra el usuario.
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRole(updatedUser.getRole());

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            userRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
