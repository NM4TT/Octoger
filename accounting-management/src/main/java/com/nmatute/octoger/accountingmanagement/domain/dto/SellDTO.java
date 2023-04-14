package com.nmatute.octoger.accountingmanagement.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SellDTO {
    
    private int id;

    private Date date;

    private ProductCollectionDTO collection;

    private UserDTO user;

    private ProductOperationDTO productOperation;

}
