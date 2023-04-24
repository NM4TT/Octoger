package com.nmatute.octoger.productmanagement.domain.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * DTO de Productos.
 * 
 * @author NM4TT
 */
@Data
public class ProductDTO {
    
    private int id;

    private ProductCollectionDTO productCollection;

    private BigDecimal price;

    private BigDecimal benefit;

    private boolean isAvailable;

}
