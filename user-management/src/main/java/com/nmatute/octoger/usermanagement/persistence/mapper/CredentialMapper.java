package com.nmatute.octoger.usermanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO.Role;
import com.nmatute.octoger.usermanagement.persistence.entity.Credential;
import com.nmatute.octoger.usermanagement.web.security.AES;
import com.nmatute.octoger.usermanagement.web.security.AES.Action;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CredentialMapper {
    
    @Mappings({
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "password", target = "password"),
        @Mapping(target = "authorities", ignore = true),
        @Mapping(target = "role", ignore = true)
    })
    CredentialDTO toCredentialDTO(Credential credential);

    @InheritInverseConfiguration
    Credential toCredential(CredentialDTO credentialDTO);

    List<CredentialDTO> toCredentialDTOs(List<Credential> credentials);

    List<Credential> toCredentials(List<CredentialDTO> credentialDTOs);

    @AfterMapping
    default void setRole(@MappingTarget CredentialDTO credentialDTO, Credential credential){
        if (credential.getUser().getType().getIdentifier().endsWith("00")) {
            credentialDTO.setRole(Role.ADMIN);
        } 
        
        if (credential.getUser().getType().getIdentifier().endsWith("01")) {
            credentialDTO.setRole(Role.REGULAR);
        }
    }

    @AfterMapping
    default void setPassword(@MappingTarget CredentialDTO credentialDTO, Credential credential){
        credentialDTO.setPassword(new AES().perform(credential.getPassword(), Action.ENCRYPT));
    }

}
