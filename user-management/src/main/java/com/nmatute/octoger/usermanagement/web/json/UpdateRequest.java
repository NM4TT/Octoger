package com.nmatute.octoger.usermanagement.web.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Json
 * 
 * @author NM4TT
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UpdateRequest extends Request {
    
    private int id;

    private String personalIdentifier;

    private String name;

    private String lastname;

    private String type;

    private String username;

    private String password;
}
