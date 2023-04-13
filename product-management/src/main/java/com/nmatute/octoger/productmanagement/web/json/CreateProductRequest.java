package com.nmatute.octoger.productmanagement.web.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateProductRequest extends Request {

    private int collectionId;

    private BigDecimal price;

    private BigDecimal benefit;

}
