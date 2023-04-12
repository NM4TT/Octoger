package com.nmatute.octoger.productmanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.nmatute.octoger.productmanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.productmanagement.persistence.entity.Credential;

@Mapper(componentModel = "spring")
public interface CredentialMapper {
    
    @Mappings({
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "password", target = "password"),
        @Mapping(target = "role", ignore = true),
        @Mapping(target = "authorities", ignore = true)
    })
    CredentialDTO toCredentialDTO(Credential credential);

    @InheritInverseConfiguration
    Credential toCredential(CredentialDTO credentialDTO);

    List<CredentialDTO> toCredentialDTOs(List<Credential> credentials);

    List<Credential> toCredentials(List<CredentialDTO> credentialDTOs);

}
