package com.nmatute.octoger.accountingmanagement.web.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateTransactionRequest extends Request {

    private int id;

    private String type;

    private BigDecimal value;

    private String date;

}
