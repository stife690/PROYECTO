package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder y gestionar la entidad {@link User} en la base de datos.
 * Esta interfaz extiende {@link JpaRepository}, lo que proporciona operaciones CRUD 
 * básicas para la entidad {@link User}, así como la capacidad de definir consultas personalizadas.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Encuentra un usuario por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario.
     * @return El usuario con el correo electrónico proporcionado, o {@code null} si no existe.
     */
    User findByEmail(String email);

    /**
     * Verifica si existe un usuario con el correo electrónico especificado.
     *
     * @param email La dirección de correo electrónico a verificar.
     * @return {@code true} si un usuario con el correo electrónico proporcionado existe, 
     *         de lo contrario {@code false}.
     */
    boolean existsByEmail(String email);
}
