package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración de seguridad para la aplicación.
 * <p>
 * Esta clase configura la seguridad web de la aplicación utilizando Spring Security. 
 * Se define la política de acceso a las rutas, habilita el soporte CORS para permitir solicitudes 
 * desde el frontend (por ejemplo, una aplicación Angular), y define el uso de un 
 * codificador de contraseñas seguro con BCrypt.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Servicio para gestionar usuarios, inyectado con la anotación @Autowired y lazy loading
    @Autowired
    @Lazy
    private UserService userService;

    /**
     * Bean que configura el codificador de contraseñas usando BCrypt.
     * <p>
     * El codificador BCryptPasswordEncoder es utilizado para cifrar y verificar contraseñas
     * de manera segura.
     * </p>
     * 
     * @return El codificador de contraseñas BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean que configura el filtro de seguridad para las solicitudes HTTP.
     * <p>
     * Este método configura los permisos de acceso a las rutas, especificando que las rutas 
     * bajo "/manager-users/**" solo son accesibles para usuarios con el rol "ADMIN". Además,
     * se habilita el soporte para CORS, se deshabilita la protección CSRF (Cross-Site Request Forgery),
     * y se deshabilitan las opciones de login y logout por formulario.
     * </p>
     * 
     * @param http La configuración HTTP de seguridad proporcionada por Spring Security.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si ocurre algún error en la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/manager-users/**").hasRole("ADMIN")  // Rutas accesibles solo para ADMIN
                .anyRequest().permitAll()  // Permitir acceso a todas las demás rutas
            .and()
            .cors().configurationSource(corsConfigurationSource())  // Activar CORS
            .and()
            .csrf().disable()  // Deshabilitar protección CSRF
            .formLogin().disable()  // Deshabilitar formulario de login
            .logout().disable();  // Deshabilitar logout

        return http.build();
    }

    /**
     * Configura el origen y las políticas de CORS (Cross-Origin Resource Sharing).
     * <p>
     * Se permite que las solicitudes provengan desde "http://localhost:4200", que es comúnmente 
     * la dirección de un frontend Angular. Además, se permiten todos los métodos HTTP y 
     * encabezados, y se establece un tiempo de vida máximo para las respuestas CORS.
     * </p>
     * 
     * @return La fuente de configuración CORS con la configuración definida.
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:4200");  // Permitir solicitudes desde Angular
        configuration.addAllowedHeader("*");  // Permitir cualquier encabezado
        configuration.addAllowedMethod("*");  // Permitir cualquier método HTTP
        configuration.setMaxAge(3600L);  // Establecer el tiempo de vida del CORS preflight

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Registrar la configuración CORS para todas las rutas

        return source;
    }
}
