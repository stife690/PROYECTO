package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user.getEmail(), user.getPassword(), user.getName(), "USER");
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
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

            // Incluye el rol en la respuesta
            Map<String, Object> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
            response.put("name", user.getName());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor.");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        // Invalida la sesión y cierra la sesión del usuario
        session.invalidate();
        return ResponseEntity.ok("Sesión cerrada correctamente.");
    }
    
    @GetMapping("/user-role")
    public ResponseEntity<?> getUserRole(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return ResponseEntity.ok(role != null ? role : "GUEST");
    }

    
}