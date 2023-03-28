package com.nmatute.octoger.usermanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.persistence.entity.Credential;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CredentialMapper {
    
    CredentialDTO toCredentialDTO(Credential credential);

    Credential toCredential(CredentialDTO credentialDTO);

    List<CredentialDTO> toCredentialDTOs(List<Credential> credentials);

    List<Credential> toCredentials(List<CredentialDTO> credentialDTOs);

}
