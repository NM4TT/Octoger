package com.nmatute.octoger.productmanagement.web.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UpdateProductCollectionRequest extends Request {
    
    private int id;

    private int userId;

    private String provider;

    private BigDecimal cost;

    private String description;

    private int productQuantity;
    
}
