package com.luv2code.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Representa una categoría de productos dentro del sistema de ecommerce.
 * La clase {@link ProductCategory} mapea la tabla "product_category" en la base de datos 
 * y almacena información relacionada con las categorías de productos, como el nombre de la categoría.
 * Esta clase también gestiona la relación con los productos asociados a cada categoría.
 */
@Entity
@Table(name="product_category")
// @Data -- known bug
@Getter
@Setter
public class ProductCategory {

    /**
     * El identificador único de la categoría de producto.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * El nombre de la categoría de producto.
     * Este campo almacena el nombre de la categoría, como "Electrónica", "Ropa", etc.
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * Los productos que pertenecen a esta categoría.
     * Este campo establece una relación uno a muchos entre {@link ProductCategory} y {@link Product}.
     * Una categoría puede contener múltiples productos.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;
}
