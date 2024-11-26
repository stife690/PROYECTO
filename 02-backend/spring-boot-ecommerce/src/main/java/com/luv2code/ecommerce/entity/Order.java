package com.luv2code.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa una entidad de pedido en el sistema.
 * La clase {@link Order} mapea la tabla "orders" en la base de datos y almacena la información 
 * relacionada con un pedido, incluyendo el número de seguimiento, la cantidad total de artículos, 
 * el precio total, el estado del pedido, y las direcciones asociadas.
 * También gestiona la relación con los artículos del pedido y el cliente.
 */
@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    /**
     * El identificador único del pedido.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    /**
     * El número de seguimiento del pedido.
     * Este campo almacena un identificador único generado para rastrear el pedido.
     */
    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    /**
     * La cantidad total de artículos en el pedido.
     * Este campo almacena el número total de productos incluidos en el pedido.
     */
    @Column(name="total_quantity")
    private int totalQuantity;

    /**
     * El precio total del pedido.
     * Este campo almacena el valor total del pedido, calculado con base en los artículos y sus precios.
     */
    @Column(name="total_price")
    private BigDecimal totalPrice;

    /**
     * El estado del pedido.
     * Este campo almacena el estado actual del pedido, como "pendiente", "procesando", "enviado", etc.
     */
    @Column(name="status")
    private String status;

    /**
     * La fecha de creación del pedido.
     * Este campo es automáticamente asignado con la fecha y hora en que se creó el pedido.
     */
    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    /**
     * La fecha de la última actualización del pedido.
     * Este campo es automáticamente asignado con la fecha y hora en que se actualizó por última vez el pedido.
     */
    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    /**
     * Los artículos asociados al pedido.
     * Este campo establece una relación uno a muchos entre {@link Order} y {@link OrderItem}.
     * Un pedido puede tener varios artículos asociados.
     * La propiedad {@link CascadeType.ALL} asegura que cualquier cambio realizado en un pedido se propague a los artículos del pedido.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    /**
     * El cliente que realizó el pedido.
     * Este campo establece una relación muchos a uno entre {@link Order} y {@link Customer}.
     * Un pedido pertenece a un cliente.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * La dirección de envío del pedido.
     * Este campo establece una relación uno a uno entre {@link Order} y {@link Address} para la dirección de envío.
     * Se utiliza para asignar la dirección donde se enviará el pedido.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    /**
     * La dirección de facturación del pedido.
     * Este campo establece una relación uno a uno entre {@link Order} y {@link Address} para la dirección de facturación.
     * Se utiliza para asignar la dirección utilizada para la facturación.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    /**
     * Agrega un artículo al pedido.
     * Este método establece la relación bidireccional entre el pedido y el artículo, añadiendo el artículo a la lista 
     * de artículos y asociando el pedido con el artículo.
     * 
     * @param item El artículo a agregar al pedido.
     */
    public void add(OrderItem item) {
        if (item != null) {
            // Inicializa el conjunto de artículos si es null
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }

            // Agrega el artículo al conjunto de artículos del pedido
            orderItems.add(item);

            // Establece la relación bidireccional con el artículo
            item.setOrder(this);
        }
    }
}
