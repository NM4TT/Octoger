package com.nmatute.octoger.accountingmanagement.web.json;

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
public class UpdateProductOperationRequest extends Request {

    private long id;

    private String type;

    private long collectionId;

    private long productAmount;

    private String date;

    private long userId;

    private long transactionId;   

}
