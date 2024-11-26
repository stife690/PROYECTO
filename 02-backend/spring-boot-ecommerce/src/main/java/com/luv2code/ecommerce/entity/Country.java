package com.luv2code.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una entidad de país en el sistema.
 * La clase {@link Country} mapea la tabla "country" en la base de datos y almacena la información
 * relacionada con un país, incluyendo su código, nombre y la relación con los estados asociados.
 * Esta clase también define una relación uno a muchos con la entidad {@link State}.
 */
@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {

    /**
     * El identificador único del país.
     * Este campo es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    
    /**
     * El código del país.
     * Este campo almacena el código ISO o algún identificador único para el país (por ejemplo, "US" para Estados Unidos).
     */
    @Column(name ="code")
    private String code;
    
    /**
     * El nombre del país.
     * Este campo almacena el nombre completo del país (por ejemplo, "Estados Unidos").
     */
    @Column(name ="name")
    private String name;
    
    /**
     * La relación uno a muchos entre {@link Country} y {@link State}.
     * Este campo define la relación entre un país y sus estados o regiones. 
     * Un país puede tener múltiples estados asociados.
     * 
     * La anotación {@link JsonIgnore} se usa para evitar la serialización infinita
     * de la relación entre Country y State cuando se convierten a JSON.
     */
    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<State> state;
}
