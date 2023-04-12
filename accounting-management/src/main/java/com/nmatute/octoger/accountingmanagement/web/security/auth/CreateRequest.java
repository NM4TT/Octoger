package com.nmatute.octoger.accountingmanagement.web.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequest extends Request {

    private String identifier;

    private String description;

}