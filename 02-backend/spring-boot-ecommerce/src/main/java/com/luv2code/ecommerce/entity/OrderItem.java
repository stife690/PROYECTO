package com.luv2code.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Representa un artículo dentro de un pedido en el sistema.
 * La clase {@link OrderItem} mapea la tabla "order_item" en la base de datos 
 * y almacena información detallada sobre un artículo específico, 
 * como el precio unitario, la cantidad y el producto relacionado.
 * Además, se gestiona la relación con el pedido al que pertenece el artículo.
 */
@Entity
@Table(name="order_item")
@Getter
@Setter
public class OrderItem {

    /**
     * El identificador único del artículo en el pedido.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    /**
     * La URL de la imagen del artículo.
     * Este campo almacena la dirección web donde se encuentra la imagen del producto asociado al artículo.
     */
    @Column(name="image_url")
    private String imageUrl;

    /**
     * El precio unitario del artículo.
     * Este campo almacena el precio por unidad del producto en el momento de la compra.
     */
    @Column(name="unit_price")
    private BigDecimal unitPrice;

    /**
     * La cantidad del artículo en el pedido.
     * Este campo almacena el número de unidades del producto que se han agregado al pedido.
     */
    @Column(name="quantity")
    private int quantity;

    /**
     * El identificador del producto asociado con este artículo.
     * Este campo almacena la referencia al producto específico en el catálogo.
     */
    @Column(name="product_id")
    private Long productId;

    /**
     * El pedido al que pertenece este artículo.
     * Este campo establece una relación muchos a uno entre {@link OrderItem} y {@link Order}.
     * Un artículo pertenece a un único pedido.
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
