package com.nmatute.octoger.accountingmanagement.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class TransactionDTO {
    
    private int id;

    private TypeDTO type;

    private BigDecimal value;

    private Date date;

}
