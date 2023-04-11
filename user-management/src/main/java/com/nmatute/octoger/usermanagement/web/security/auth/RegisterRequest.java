package com.nmatute.octoger.usermanagement.web.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest extends Request {

    private String username;

    private String password;

    private String personalIdentifier;

    private String name;

    private String lastname;

    private String type;

}
