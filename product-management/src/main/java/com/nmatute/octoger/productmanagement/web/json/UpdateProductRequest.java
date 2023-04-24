package com.nmatute.octoger.productmanagement.web.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Json
 * 
 * @author NM4TT
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UpdateProductRequest extends Request {
    
    private int id;

    private int collectionId;

    private BigDecimal price;

    private BigDecimal benefit;

    private boolean isAvailable;
    
}
