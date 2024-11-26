package com.luv2code.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.luv2code.ecommerce.entity.State;
import java.util.List;

/**
 * Repositorio para acceder y gestionar la entidad {@link State} en la base de datos.
 * Esta interfaz extiende {@link JpaRepository}, proporcionando operaciones CRUD básicas 
 * sobre la entidad {@link State}, y también ofrece un método personalizado para la 
 * búsqueda de estados por código de país.
 * 
 * <p>El repositorio está configurado para permitir solicitudes desde la URL 
 * "http://localhost:4200" mediante la anotación {@link CrossOrigin}, lo que habilita la 
 * interoperabilidad con aplicaciones frontend ejecutándose en esa dirección.</p>
 * 
 * <p>El método definido permite buscar los estados en base al código de país 
 * proporcionado, devolviendo una lista de los estados que corresponden a dicho código.</p>
 */
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {

    /**
     * Encuentra una lista de estados que pertenecen a un país con el código especificado.
     * 
     * @param code El código del país cuya lista de estados se desea obtener.
     * @return Una lista de estados que corresponden al código de país dado.
     */
    List<State> findByCountryCode(@Param("code") String code);
}


//SELECT * FROM state WHERE country_code = :code;
//Este repositorio facilita la interacción con la base de datos para la entidad