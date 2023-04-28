package com.nmatute.octoger.productmanagement.persistence.entity;

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
 * Entidad de Productos.
 * 
 * @author NM4TT
 */
@Entity
@Table(name = "product", schema = "public")
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "id")
    private ProductCollection productCollection;

    private BigDecimal price;

    private BigDecimal benefit;

    @Column(name = "is_available")
    private boolean isAvailable;

}
