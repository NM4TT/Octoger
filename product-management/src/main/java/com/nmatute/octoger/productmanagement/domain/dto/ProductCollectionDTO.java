package com.nmatute.octoger.productmanagement.domain.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * DTO de Coleccion de Productos.
 * 
 * @author NM4TT
 */
@Data
public class ProductCollectionDTO {
    
    private int id;

    private UserDTO user;

    private String provider;

    private BigDecimal cost;

    private String description;

    private int productQuantity;

}
