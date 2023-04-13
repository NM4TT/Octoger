package com.nmatute.octoger.productmanagement.web.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateProductRequest extends Request {
    
    private int id;

    private int collectionId;

    private BigDecimal price;

    private BigDecimal benefit;

    private boolean isAvailable;
    
}
