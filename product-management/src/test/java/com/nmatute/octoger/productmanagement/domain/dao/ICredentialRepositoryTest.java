package com.nmatute.octoger.productmanagement.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.productmanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.mapper.CredentialMapper;
import com.nmatute.octoger.productmanagement.persistence.repository.CredentialRepository;

/**
 * Clase para testear Repositorio de Credenciales en web service.
 * 
 * @author NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ICredentialRepositoryTest {
    
    @Mock
    private ICredentialCrudRepository crud;
    @Mock
    private CredentialMapper mapper;
    @InjectMocks
    private CredentialRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsername(){
        CredentialDTO credential = new CredentialDTO();
        credential.setUsername("username");
        when(repo.findByUsername("username")).thenReturn(credential);

        CredentialDTO credentialFound = repo.findByUsername("username");

        assertEquals(credential, credentialFound);
    }

}
