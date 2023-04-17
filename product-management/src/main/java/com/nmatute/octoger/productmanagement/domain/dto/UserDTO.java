package com.nmatute.octoger.productmanagement.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
    
    private int id;

    private String personalIdentifier;

    private String name;

    private String lastname;

    private TypeDTO type;
}
