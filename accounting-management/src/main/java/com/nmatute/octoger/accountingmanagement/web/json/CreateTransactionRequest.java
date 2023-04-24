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
public class CreateTransactionRequest extends Request {

    private String type;

    private BigDecimal value;

    private String date;

}
