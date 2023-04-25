package com.nmatute.octoger.accountingmanagement.domain.dto;

import java.util.Date;

import lombok.Data;

/**
 * DTO de Operacion de Productos.
 * 
 * @author NM4TT
 */
@Data
public class ProductOperationDTO {

    private long id;

    private TypeDTO type;

    private ProductCollectionDTO collection;

    private long productAmount;

    private Date date;

    private UserDTO user;

    private TransactionDTO transaction;   
}
