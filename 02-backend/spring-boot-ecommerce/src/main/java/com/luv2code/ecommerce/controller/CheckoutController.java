package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.luv2code.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que gestiona el proceso de compra.
 * Proporciona un endpoint para realizar un pedido y procesarlo.
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    /**
     * Constructor para inicializar el servicio de checkout.
     *
     * @param checkoutService Servicio encargado de gestionar las compras.
     */
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    /**
     * Procesa un pedido y devuelve una respuesta con los detalles de la compra.
     *
     * @param purchase Objeto que contiene la información de la compra.
     * @return Objeto {@link PurchaseResponse} con los detalles del pedido procesado.
     */
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }
}
