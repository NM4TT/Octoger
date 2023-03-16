package com.nmatute.octoger.usermanagement.domain.dto;

import lombok.Data;

@Data
public class CredentialDTO {
 
    private int id;

    private int userId;

    private String username;

    private String password;
    
}
