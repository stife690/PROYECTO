package com.luv2code.ecommerce.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Representa un producto dentro del sistema de ecommerce.
 * La clase {@link Product} mapea la tabla "product" en la base de datos 
 * y almacena información relacionada con el producto, como su nombre, precio, 
 * descripción, disponibilidad, y la categoría a la que pertenece.
 * Esta clase también gestiona la relación con la categoría de producto correspondiente.
 */
@Entity
@Table(name="product")
@Data
public class Product {

    /**
     * El identificador único del producto.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * La categoría del producto.
     * Este campo establece una relación muchos a uno entre {@link Product} y {@link ProductCategory}.
     * Un producto pertenece a una categoría específica.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    /**
     * El código de referencia del producto.
     * Este campo almacena un identificador único de stock de la unidad (SKU) del producto.
     */
    @Column(name = "sku")
    private String sku;

    /**
     * El nombre del producto.
     * Este campo almacena el nombre del producto tal como se mostrará en la tienda en línea.
     */
    @Column(name = "name")
    private String name;

    /**
     * La descripción del producto.
     * Este campo almacena una descripción detallada del producto.
     */
    @Column(name = "description")
    private String description;

    /**
     * El precio unitario del producto.
     * Este campo almacena el precio de una unidad del producto.
     */
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    /**
     * La URL de la imagen del producto.
     * Este campo almacena la dirección web donde se encuentra la imagen del producto.
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * Estado de disponibilidad del producto.
     * Este campo indica si el producto está activo o descontinuado en la tienda en línea.
     */
    @Column(name = "active")
    private boolean active;

    /**
     * El número de unidades del producto disponibles en stock.
     * Este campo almacena la cantidad de unidades disponibles para la venta del producto.
     */
    @Column(name = "units_in_stock")
    private int unitsInStock;

    /**
     * Fecha de creación del producto.
     * Este campo se llena automáticamente con la fecha y hora en que se creó el producto en la base de datos.
     */
    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    /**
     * Fecha de la última actualización del producto.
     * Este campo se actualiza automáticamente cada vez que el producto es modificado en la base de datos.
     */
    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
}
