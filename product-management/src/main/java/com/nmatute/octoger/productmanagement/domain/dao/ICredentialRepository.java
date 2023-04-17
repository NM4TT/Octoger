package com.nmatute.octoger.productmanagement.domain.dao;

import com.nmatute.octoger.productmanagement.domain.dto.CredentialDTO;

public interface ICredentialRepository {
    CredentialDTO findByUsername(String username);
}
