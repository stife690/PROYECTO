package com.luv2code.ecommerce.service;


import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

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