package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Country;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Configuración personalizada para la exposición de repositorios REST en una aplicación Spring.
 * <p>
 * Esta clase implementa {@link RepositoryRestConfigurer} para personalizar la configuración
 * de los repositorios REST, incluyendo la desactivación de ciertos métodos HTTP y la exposición
 * de los identificadores (IDs) de las entidades a través de los endpoints REST.
 * </p>
 * <p>
 * Esta configuración también establece restricciones en los métodos HTTP disponibles para 
 * las entidades `Product`, `ProductCategory`, `Country`, y `State`, deshabilitando las 
 * acciones PUT, POST, DELETE y PATCH.
 * </p>
 */
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    // Administrador de entidades JPA para acceder al metamodelo de las entidades
    private EntityManager entityManager;

    /**
     * Constructor que inicializa el administrador de entidades.
     * 
     * @param theEntityManager El {@link EntityManager} utilizado para acceder al metamodelo
     *                         de las entidades en la base de datos.
     */
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    /**
     * Configura los repositorios REST, deshabilitando ciertos métodos HTTP para algunas entidades
     * y exponiendo los identificadores (IDs) de las entidades en los endpoints REST.
     * 
     * @param config La configuración del repositorio REST.
     * @param cors   La configuración de CORS (Cross-Origin Resource Sharing).
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        // Métodos HTTP que se deshabilitarán (PUT, POST, DELETE, PATCH)
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        // Deshabilita los métodos HTTP no soportados para las entidades especificadas
        disableHttpMethods(Product.class, config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
        disableHttpMethods(Country.class, config, theUnsupportedActions);
        disableHttpMethods(State.class, config, theUnsupportedActions);

        // Exponer los IDs de las entidades
        exposeIds(config);
    }

    /**
     * Deshabilita los métodos HTTP especificados para una entidad en particular.
     * 
     * @param theClass        La clase de la entidad sobre la que se deshabilitarán los métodos HTTP.
     * @param config          La configuración del repositorio REST.
     * @param theUnsupportedActions Los métodos HTTP a deshabilitar.
     */
    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    /**
     * Expone los identificadores (IDs) de todas las entidades gestionadas por el repositorio REST.
     * 
     * @param config La configuración del repositorio REST.
     */
    private void exposeIds(RepositoryRestConfiguration config) {

        // Obtener todas las clases de entidad del metamodelo
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // Crear una lista para almacenar las clases de entidad
        List<Class> entityClasses = new ArrayList<>();

        // Añadir las clases de entidad a la lista
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        // Exponer los identificadores de todas las entidades
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
