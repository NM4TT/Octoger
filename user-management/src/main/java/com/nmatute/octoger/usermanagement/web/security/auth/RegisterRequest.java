package com.nmatute.octoger.usermanagement.web.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastname;
    private String personalIdentifier;
    private String type;
    private String username;
    private String password;

}
