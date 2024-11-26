package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.ecommerce.entity.Customer;

/**
 * Repositorio para acceder y gestionar la entidad {@link Customer} en la base de datos.
 * Esta interfaz extiende {@link JpaRepository}, lo que proporciona operaciones CRUD básicas 
 * para la entidad {@link Customer}, así como la capacidad de definir consultas personalizadas.
 * 
 * <p>El repositorio facilita la interacción con la base de datos para realizar operaciones como 
 * guardar, eliminar, y buscar clientes en la aplicación de comercio electrónico.</p>
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // No es necesario declarar métodos adicionales, ya que JpaRepository ya proporciona operaciones CRUD básicas.
}
