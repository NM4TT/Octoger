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
public class CreateProductCollectionRequest extends Request {

    private long userId;

    private String provider;

    private BigDecimal cost;

    private String description;

    private long productQuantity;

}
