package com.nmatute.octoger.accountingmanagement.domain.dto;

import java.util.Date;

import lombok.Data;

/**
 * DTO de Ventas.
 * 
 * @author NM4TT
 */
@Data
public class SellDTO {
    
    private int id;

    private Date date;

    private ProductCollectionDTO collection;

    private UserDTO user;

    private ProductOperationDTO productOperation;

}
