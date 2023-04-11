package com.nmatute.octoger.usermanagement.domain.dao;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;

public interface ICredentialRepository {
    
    CredentialDTO get(String username);

    CredentialDTO save(CredentialDTO credential);

}
