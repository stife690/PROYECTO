package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Repositorio para acceder y gestionar la entidad {@link Product} en la base de datos.
 * Esta interfaz extiende {@link JpaRepository}, proporcionando operaciones CRUD básicas 
 * sobre la entidad {@link Product}, y también ofrece métodos personalizados para la 
 * búsqueda de productos en la base de datos.
 * 
 * <p>El repositorio está configurado para permitir solicitudes desde la URL 
 * "http://localhost:4200" mediante la anotación {@link CrossOrigin}, lo que habilita la 
 * interoperabilidad con aplicaciones frontend ejecutándose en esa dirección.</p>
 * 
 * <p>Los métodos definidos permiten realizar consultas sobre los productos en base a su 
 * categoría o por su nombre utilizando la paginación proporcionada por {@link Pageable}.</p>
 */
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
	
    /**
     * Encuentra una página de productos que pertenezcan a una categoría específica.
     * 
     * @param id El ID de la categoría de los productos.
     * @param pageable Los parámetros de paginación.
     * @return Una página de productos de la categoría especificada.
     */
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
    
    /**
     * Encuentra una página de productos cuyo nombre contenga una cadena de texto específica.
     * 
     * @param name El fragmento de nombre que se desea buscar en los productos.
     * @param pageable Los parámetros de paginación.
     * @return Una página de productos cuyos nombres contienen el texto dado.
     */
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
