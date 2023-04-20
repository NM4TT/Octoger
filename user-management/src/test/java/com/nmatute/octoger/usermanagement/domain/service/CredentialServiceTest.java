package com.nmatute.octoger.usermanagement.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.CredentialRepository;

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
    void testFindByUsername() {
        CredentialDTO credential = new CredentialDTO();
        credential.setUsername("username");
        when(service.findByUsername("username")).thenReturn(credential);
        
        CredentialDTO credentialFound = repo.findByUsername("username");

        assertEquals(credentialFound, credential);
    }

    @Test
    void testFindIdByUsername() {
        CredentialDTO credential = new CredentialDTO();
        credential.setId(1);
        credential.setUsername("username");
        when(service.findIdByUsername("username")).thenReturn(1);
        
        int result = service.findIdByUsername("username");

        assertEquals(1,result);
    }

    @Test
    void testSave() {
        CredentialDTO credential = new CredentialDTO();
        when(service.save(credential)).thenReturn(credential);

        CredentialDTO savedCredential = service.save(credential);

        assertEquals(credential, savedCredential);
    }
}
