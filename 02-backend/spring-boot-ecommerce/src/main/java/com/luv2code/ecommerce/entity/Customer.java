package com.luv2code.ecommerce.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una entidad de cliente en el sistema.
 * La clase {@link Customer} mapea la tabla "customer" en la base de datos y almacena la información
 * relacionada con un cliente, incluyendo su nombre, apellido, correo electrónico y los pedidos asociados a él.
 * Esta clase también establece una relación uno a muchos con la entidad {@link Order}.
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    /**
     * El identificador único del cliente.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * El primer nombre del cliente.
     * Este campo almacena el primer nombre del cliente.
     */
    @Column(name = "first_name")
    private String firstName;
    
    /**
     * El apellido del cliente.
     * Este campo almacena el apellido del cliente.
     */
    @Column(name = "last_name")
    private String lastName;
    
    /**
     * El correo electrónico del cliente.
     * Este campo almacena el correo electrónico único del cliente, utilizado para la autenticación y comunicaciones.
     */
    @Column(name = "email")
    private String email;
    
    /**
     * La relación uno a muchos entre {@link Customer} y {@link Order}.
     * Este campo define la relación entre un cliente y los pedidos que ha realizado. 
     * Un cliente puede tener múltiples pedidos asociados.
     * La propiedad {@link CascadeType.ALL} asegura que cualquier cambio realizado en un cliente se propague a los pedidos asociados.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();
    
    /**
     * Agrega un pedido a la lista de pedidos del cliente.
     * Este método establece la relación bidireccional entre el cliente y el pedido.
     * 
     * @param order El pedido a agregar.
     */
    public void add(Order order) {
        if (order != null) {
            // Inicializa el conjunto de pedidos si es null
            if (orders == null) {
                orders = new HashSet<>();
            }

            // Agrega el pedido a la lista de pedidos
            orders.add(order);

            // Establece la relación bidireccional
            order.setCustomer(this);
        }
    }
}
