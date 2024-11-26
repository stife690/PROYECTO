package com.luv2code.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

/**
 * Representa una entidad de usuario en el sistema.
 * La clase {@link User} mapea la tabla "users" en la base de datos y almacena la información
 * relacionada con un usuario, incluyendo su correo electrónico, contraseña, nombre y rol.
 * Esta clase también implementa la interfaz {@link org.springframework.security.core.userdetails.UserDetails}
 * para ser utilizada con Spring Security, proporcionando las autoridades del usuario.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * El identificador único del usuario.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * El correo electrónico del usuario.
     * Este campo se utiliza como el nombre de usuario para la autenticación.
     */
    private String email;
    
    /**
     * La contraseña del usuario.
     * Este campo almacena la contraseña en formato cifrado.
     */
    private String password;
    
    /**
     * El nombre completo del usuario.
     * Este campo contiene el nombre del usuario.
     */
    private String name;
    
    /**
     * El rol del usuario en el sistema.
     * Este campo indica el rol (por ejemplo, "ADMIN", "USER") que el usuario tiene.
     */
    private String role;

    // Constructor, Getters y Setters

    /**
     * Constructor por defecto.
     * Crea una nueva instancia de {@link User} sin establecer valores.
     */
    public User() {}

    /**
     * Constructor que inicializa un nuevo usuario con los valores proporcionados.
     * 
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @param name El nombre del usuario.
     * @param role El rol asignado al usuario.
     */
    public User(String email, String password, String name, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    /**
     * Obtiene el identificador único del usuario.
     * 
     * @return El identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     * 
     * @param id El identificador único del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el nombre completo del usuario.
     * 
     * @return El nombre completo del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre completo del usuario.
     * 
     * @param name El nombre completo del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el rol del usuario.
     * 
     * @return El rol asignado al usuario.
     */
    public String getRole() {
        return role;
    }

    /**
     * Establece el rol del usuario.
     * 
     * @param role El rol asignado al usuario.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Devuelve las autoridades del usuario basadas en su rol.
     * Este método es utilizado por Spring Security para autorizar al usuario en función de su rol.
     * 
     * @return Una colección de objetos {@link GrantedAuthority} que representan las autoridades del usuario.
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + this.role));
    }
}
