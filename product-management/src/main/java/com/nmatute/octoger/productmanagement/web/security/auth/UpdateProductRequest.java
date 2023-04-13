package com.nmatute.octoger.productmanagement.web.security.auth;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest extends Request {
    
    private int id;

    private int productCollectionId;

    private BigDecimal price;

    private BigDecimal benefit;

    private boolean isAvailable;
    
}
