package com.luv2code.ecommerce.dto;

import lombok.Data;

/**
 * Representa la respuesta de una compra realizada en el sistema de ecommerce.
 * La clase {@link PurchaseResponse} contiene el número de seguimiento de un pedido,
 * que se utiliza para rastrear el estado del pedido a lo largo de su proceso.
 */
@Data
public class PurchaseResponse {

    /**
     * El número de seguimiento del pedido.
     * Este campo almacena el identificador único del pedido que se genera al realizar una compra.
     */
    private final String orderTrackingNumber;

}
