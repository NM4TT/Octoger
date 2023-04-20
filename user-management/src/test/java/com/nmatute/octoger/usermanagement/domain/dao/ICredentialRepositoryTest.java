package com.nmatute.octoger.usermanagement.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.entity.Credential;
import com.nmatute.octoger.usermanagement.persistence.mapper.CredentialMapper;
import com.nmatute.octoger.usermanagement.persistence.repository.CredentialRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class ICredentialRepositoryTest {
    
    @Mock
    private ICredentialCrudRepository crud;
    @Mock
    private IUserCrudRepository userCrud;
    @Mock
    private CredentialMapper mapper;

    @InjectMocks
    private CredentialRepository repo;

    private CredentialDTO credentialDTO;
    private Credential credential;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);

        credential = new Credential();
        credential.setPassword("password");
        credential.setUsername("username");
        
        credentialDTO = new CredentialDTO();
        credentialDTO.setPassword("password");
        credentialDTO.setUsername("username");
    }
    
    @Test
    void testFindByUsername() {
        when(mapper.toCredentialDTO(credential)).thenReturn(credentialDTO);
        when(crud.findByUsername(any(String.class))).thenReturn(credential);
        
        CredentialDTO credentialFound = mapper.toCredentialDTO(crud.findByUsername("test"));

        assertEquals(credentialFound.getUsername(), credential.getUsername());
        assertEquals(credentialFound.getPassword(), credential.getPassword());
    }

    @Test
    void testGetUserType() {
        when(userCrud.getUserType(anyInt()))
        .thenReturn(anyString());
        
        String result = userCrud.getUserType(5);

        assertNotNull(result);
    }

    @Test
    void testSave() {
        when(mapper.toCredential(credentialDTO)).thenReturn(credential);
        when(mapper.toCredentialDTO(credential)).thenReturn(credentialDTO);
        when(crud.save(any(Credential.class))).thenReturn(credential);

        CredentialDTO savedCredential = mapper.toCredentialDTO(crud.save(mapper.toCredential(credentialDTO)));

        assertNotNull(savedCredential);
        assertEquals(credentialDTO.getUsername(), savedCredential.getUsername());
        assertEquals(credentialDTO.getPassword(), savedCredential.getPassword());
    }
}
