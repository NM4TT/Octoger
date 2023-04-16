package com.nmatute.octoger.accountingmanagement.web.json;

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
public class UpdateSellRequest extends Request {

    private int id;

    private String date;

    private int collectionId;

    private int userId;

    private int productOperationId;

}
