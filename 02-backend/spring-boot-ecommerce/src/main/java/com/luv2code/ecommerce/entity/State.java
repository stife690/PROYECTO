package com.luv2code.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Representa una entidad de estado en la base de datos.
 * La clase {@link State} mapea la tabla "state" y almacena la información sobre un estado,
 * incluyendo su nombre y la relación con el país al que pertenece.
 */
@Entity
@Table(name = "state")
@Data
public class State {
    
    /**
     * El identificador único del estado.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    /**
     * El nombre del estado.
     * Este campo almacena el nombre del estado (por ejemplo, "California").
     */
    @Column(name = "name")
    private String name;
    
    /**
     * La relación entre el estado y su país correspondiente.
     * Este campo representa una relación de muchos a uno entre {@link State} y {@link Country}.
     * Un estado pertenece a un único país.
     */
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    
}
