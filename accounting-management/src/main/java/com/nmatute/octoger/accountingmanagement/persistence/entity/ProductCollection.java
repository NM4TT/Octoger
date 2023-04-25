package com.nmatute.octoger.accountingmanagement.persistence.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Clase para Entidad de Coleccion de Productos.
 * 
 * @author NM4TT
 */
@Entity
@Table(name = "product_collection", schema = "public")
@Data
public class ProductCollection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String provider;

    private BigDecimal cost;

    private String description;

    @Column(name = "product_quantity")
    private long productQuantity;

}
