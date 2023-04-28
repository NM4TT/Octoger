package com.nmatute.octoger.productmanagement.domain.dao;

import com.nmatute.octoger.productmanagement.domain.dto.CredentialDTO;

/**
 * DAO de Credenciales.
 * 
 * @author NM4TT
 */
public interface ICredentialRepository {
    CredentialDTO findByUsername(String username);
}
