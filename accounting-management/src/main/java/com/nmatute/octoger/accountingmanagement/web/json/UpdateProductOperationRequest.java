package com.nmatute.octoger.accountingmanagement.web.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
public class UpdateProductOperationRequest extends Request {

    private int id;

    private String type;

    private int collectionId;

    private int productAmount;

    private String date;

    private int userId;

    private int transactionId;   

}
