package com.luv2code.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.luv2code.ecommerce.entity.Country;

/**
 * Repositorio para acceder y gestionar la entidad {@link Country} en la base de datos.
 * Esta interfaz extiende {@link JpaRepository}, proporcionando operaciones CRUD básicas
 * sobre la entidad {@link Country}, y también se utiliza para exponer el repositorio como
 * un servicio REST a través de Spring Data REST.
 * 
 * <p>El repositorio está configurado para permitir solicitudes desde la URL 
 * "http://localhost:4200" mediante la anotación {@link CrossOrigin}, lo que habilita la 
 * interoperabilidad con aplicaciones frontend ejecutándose en esa dirección.</p>
 * 
 * Además, la interfaz está anotada con {@link RepositoryRestResource} para personalizar 
 * la URL de la colección y la relación de recursos para las operaciones REST, con el fin 
 * de exponer los datos de {@link Country} a través de un servicio RESTful.
 */
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {
    // No es necesario declarar métodos adicionales, ya que JpaRepository ya proporciona operaciones CRUD básicas.
}
