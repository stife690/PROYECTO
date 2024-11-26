package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio personalizado para cargar los detalles del usuario a través de su correo electrónico.
 * Esta clase implementa la interfaz {@link UserDetailsService} de Spring Security y se utiliza para cargar un usuario
 * cuando se autentica a través de su nombre de usuario (en este caso, su correo electrónico).
 */
@Service
@Lazy
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Carga los detalles del usuario a partir del nombre de usuario proporcionado (en este caso, el correo electrónico).
     * 
     * @param username El nombre de usuario (correo electrónico) del usuario a cargar.
     * @return Un objeto {@link UserDetails} que contiene la información del usuario (como correo, contraseña y roles).
     * @throws UsernameNotFoundException Si no se encuentra un usuario con el correo electrónico proporcionado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario por su email (puedes ajustarlo a tu campo de login)
        User user = userRepository.findByEmail(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el correo: " + username);
        }
        
        // Devolver un objeto UserDetails (para usar con Spring Security)
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities() // Asegúrate de que tu entidad User tenga roles correctamente configurados
        );
    }
}
