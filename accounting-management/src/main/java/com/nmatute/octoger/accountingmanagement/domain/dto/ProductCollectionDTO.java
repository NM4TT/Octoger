package com.nmatute.octoger.accountingmanagement.domain.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductCollectionDTO {
    
    private int id;

    private UserDTO user;

    private String provider;

    private BigDecimal cost;

    private String description;

    private int productQuantity;

}
