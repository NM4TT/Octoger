package com.nmatute.octoger.typemanagement.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.typemanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.typemanagement.persistence.repository.CredentialRepository;

/**
 * Probar Servicio de Credenciales en Web Service.
 *
 * @author: NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class CredentialServiceTest {

    @Mock
    private CredentialRepository repo;

    @InjectMocks
    private CredentialService service;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsername(){
        CredentialDTO credential = new CredentialDTO();
        credential.setUsername("username");
        when(service.findByUsername("username")).thenReturn(credential);

        CredentialDTO credentialFound = service.findByUsername("username");

        assertEquals(credential, credentialFound);
    }
    
}
