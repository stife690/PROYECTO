package com.luv2code.ecommerce.service;

import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

/**
 * Implementación del servicio de checkout que maneja el proceso de realizar un pedido.
 * Este servicio se encarga de procesar la compra, generar un número de seguimiento para el pedido y almacenar la información
 * en la base de datos.
 */
@Service
public class CheckoutServicelmpl implements CheckoutService {

    private CustomerRepository customerRepository;

    /**
     * Constructor que inyecta el repositorio de clientes.
     * 
     * @param customerRepository El repositorio de clientes utilizado para almacenar la información en la base de datos.
     */
    public CheckoutServicelmpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Procesa un pedido y lo guarda en la base de datos.
     * Este método crea un nuevo pedido, lo llena con los artículos y las direcciones de facturación y envío,
     * y lo asocia con el cliente correspondiente.
     * 
     * @param purchase El objeto de tipo {@link Purchase} que contiene la información del pedido, artículos y cliente.
     * @return Un objeto {@link PurchaseResponse} con el número de seguimiento generado para el pedido.
     */
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Recupera la información del pedido desde el DTO
        Order order = purchase.getOrder();

        // Genera un número de seguimiento para el pedido
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // Agrega los artículos del pedido al pedido
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // Establece las direcciones de facturación y envío en el pedido
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // Asocia el pedido al cliente
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // Guarda el cliente con el pedido en la base de datos
        customerRepository.save(customer);

        // Devuelve una respuesta con el número de seguimiento del pedido
        return new PurchaseResponse(orderTrackingNumber);
    }

    /**
     * Genera un número único de seguimiento para el pedido utilizando UUID.
     * 
     * @return Un número de seguimiento único generado aleatoriamente.
     */
    private String generateOrderTrackingNumber() {

        // Genera un número UUID aleatorio (UUID versión-4)
        // Para más detalles, consulta: https://en.wikipedia.org/wiki/Universally_unique_identifier
        return UUID.randomUUID().toString();
    }
}
