package com.nmatute.octoger.typemanagement.domain.dto;

import lombok.Data;

/**
 * DTO de Usuarios.
 * 
 * @author NM4TT
 */
@Data
public class UserDTO {
    
    private long id;

    private String personalIdentifier;

    private String name;

    private String lastname;

    private TypeDTO type;
}
