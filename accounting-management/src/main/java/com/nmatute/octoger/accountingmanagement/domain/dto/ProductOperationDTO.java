package com.nmatute.octoger.accountingmanagement.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductOperationDTO {

    private int id;

    private TypeDTO type;

    private ProductCollectionDTO collection;

    private int productAmount;

    private Date date;

    private UserDTO user;

    private TransactionDTO transaction;   
}