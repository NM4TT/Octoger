package com.nmatute.octoger.usermanagement.domain.dao;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;

public interface ICredentialRepository {
    
    CredentialDTO get(int userId);

    String getUsername(int userId);

    String getPassword(int userId);

    CredentialDTO save(CredentialDTO credential);

}
