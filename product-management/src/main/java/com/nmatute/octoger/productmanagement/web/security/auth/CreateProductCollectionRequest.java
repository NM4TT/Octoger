package com.nmatute.octoger.productmanagement.web.security.auth;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateProductCollectionRequest extends Request {

    private int userId;

    private String provider;

    private BigDecimal cost;

    private String description;

    private int productQuantity;

}
