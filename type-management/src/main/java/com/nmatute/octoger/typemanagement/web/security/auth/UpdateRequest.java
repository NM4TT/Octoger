package com.nmatute.octoger.typemanagement.web.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest extends Request {
    
    private int id;

    private String identifier;

    private String description;
}
