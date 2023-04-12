package com.nmatute.octoger.typemanagement.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
    
    private int id;

    private String personalIdentifier;

    private String name;

    private String lastname;

    private String type;
}