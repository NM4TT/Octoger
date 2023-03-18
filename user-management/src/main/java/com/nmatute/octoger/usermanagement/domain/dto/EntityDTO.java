package com.nmatute.octoger.usermanagement.domain.dto;

import lombok.Data;

@Data
public class EntityDTO {
    private UserDTO user;
    private CredentialDTO credential;
}
