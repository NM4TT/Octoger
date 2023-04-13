package com.nmatute.octoger.productmanagement.domain.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {
    
    private int id;

    private ProductCollectionDTO productCollection;

    private BigDecimal price;

    private BigDecimal benefit;

    private boolean isAvailable;

}
