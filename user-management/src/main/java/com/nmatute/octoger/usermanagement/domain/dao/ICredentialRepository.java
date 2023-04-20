package com.nmatute.octoger.usermanagement.domain.dao;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;

public interface ICredentialRepository {
    
    CredentialDTO save(CredentialDTO credential);

    int findIdByUsername(String username);

    CredentialDTO findByUsername(String username);

    String getUserType(int userId);

}
