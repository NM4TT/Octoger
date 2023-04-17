package com.nmatute.octoger.usermanagement.web.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RegisterRequest extends Request {

    private String username;

    private String password;

    private String personalIdentifier;

    private String name;

    private String lastname;

    private String type;

}
