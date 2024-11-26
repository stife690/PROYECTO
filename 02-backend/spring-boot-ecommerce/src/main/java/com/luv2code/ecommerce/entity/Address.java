package com.luv2code.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una entidad de dirección en el sistema.
 * La clase {@link Address} mapea la tabla "address" en la base de datos y almacena la información
 * relacionada con una dirección, incluyendo la calle, ciudad, estado, país, código postal y la relación con un pedido.
 * Esta clase se utiliza para asociar una dirección de facturación o envío a un pedido.
 */
@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    /**
     * El identificador único de la dirección.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * La calle de la dirección.
     * Este campo almacena la dirección específica de la calle de la dirección.
     */
    @Column(name = "street")
    private String street;
    
    /**
     * La ciudad de la dirección.
     * Este campo almacena la ciudad asociada a la dirección.
     */
    @Column(name = "city")
    private String city;
    
    /**
     * El estado de la dirección.
     * Este campo almacena el estado o la región asociada a la dirección.
     */
    @Column(name = "state")
    private String state;
    
    /**
     * El país de la dirección.
     * Este campo almacena el país donde se encuentra la dirección.
     */
    @Column(name = "country")
    private String country;
    
    /**
     * El código postal de la dirección.
     * Este campo almacena el código postal asociado a la dirección.
     */
    @Column(name = "zipCode")
    private String zipCode;
    
    /**
     * La relación entre la dirección y el pedido.
     * Este campo establece una relación uno a uno entre la entidad {@link Address} y {@link Order},
     * uniendo las dos entidades a través de la clave primaria del pedido.
     */
    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;
}
