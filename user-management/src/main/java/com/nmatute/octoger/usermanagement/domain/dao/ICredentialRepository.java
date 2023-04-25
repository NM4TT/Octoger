package com.nmatute.octoger.usermanagement.domain.dao;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;

/**
 * Interfaz DAO para Repositorio de Credenciales.
 * 
 * @author NM4TT
 */
public interface ICredentialRepository {
    
    CredentialDTO save(CredentialDTO credential);

    long findIdByUsername(String username);

    CredentialDTO findByUsername(String username);

    String getUserType(long userId);

}
