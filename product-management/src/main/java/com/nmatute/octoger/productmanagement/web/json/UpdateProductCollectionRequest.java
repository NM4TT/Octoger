package com.nmatute.octoger.productmanagement.web.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateProductCollectionRequest extends Request {
    
    private int id;

    private int userId;

    private String provider;

    private BigDecimal cost;

    private String description;

    private int productQuantity;
    
}