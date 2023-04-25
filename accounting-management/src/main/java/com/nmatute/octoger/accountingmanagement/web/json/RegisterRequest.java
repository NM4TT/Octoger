package com.nmatute.octoger.accountingmanagement.web.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Json
 * 
 * @author NM4TT
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
public class RegisterRequest extends Request{
    
    private String type;

    private String date; 

    private boolean isSell; 

    private BigDecimal value;
    
    private long productAmount; 
    
    private long collectionId;

    private long userId;

}
