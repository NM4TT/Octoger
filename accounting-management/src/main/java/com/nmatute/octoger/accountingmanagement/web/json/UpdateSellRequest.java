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
public class UpdateSellRequest extends Request {

    private long id;

    private String date;

    private long collectionId;

    private long userId;

    private long productOperationId;

}
