package com.luv2code.ecommerce.dto;

import java.util.Set;

import com.luv2code.ecommerce.entity.Address;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;

import lombok.Data;

/**
 * Representa una compra realizada en el sistema de ecommerce.
 * La clase {@link Purchase} agrupa toda la información relevante de una compra,
 * incluyendo al cliente, las direcciones de envío y facturación, el pedido y los artículos del pedido.
 */
@Data
public class Purchase {
    
    /**
     * El cliente que realizó la compra.
     * Este campo contiene la información del cliente, como su nombre y correo electrónico.
     */
    private Customer customer;

    /**
     * La dirección de envío del pedido.
     * Este campo almacena la dirección a la que se enviarán los productos del pedido.
     */
    private Address shippingAddress;

    /**
     * La dirección de facturación del pedido.
     * Este campo almacena la dirección utilizada para facturación.
     */
    private Address billingAddress;

    /**
     * El pedido asociado a la compra.
     * Este campo contiene la información detallada del pedido, como el número de seguimiento, el precio total, etc.
     */
    private Order order;

    /**
     * Los artículos que componen el pedido.
     * Este campo almacena una lista de {@link OrderItem} que incluye los productos adquiridos en la compra.
     */
    private Set<OrderItem> orderItems;
}
