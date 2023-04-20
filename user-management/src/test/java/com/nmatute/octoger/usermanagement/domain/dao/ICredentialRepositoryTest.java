package com.nmatute.octoger.usermanagement.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.mapper.CredentialMapper;
import com.nmatute.octoger.usermanagement.persistence.repository.CredentialRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class ICredentialRepositoryTest {
    
    @Mock
    private CredentialMapper mapper;
    @Mock
    private ICredentialCrudRepository crud;
    @Mock
    private IUserCrudRepository userCrud;

    @InjectMocks
    private CredentialRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindByUsername() {
        CredentialDTO credential = new CredentialDTO();
        credential.setUsername("username");
        when(repo.findByUsername("username")).thenReturn(credential);
        
        CredentialDTO credentialFound = repo.findByUsername("username");

        assertEquals(credential, credentialFound);
    }

    @Test
    void testGetUserType() {
        CredentialDTO credential = new CredentialDTO();
        UserDTO user = new UserDTO();
        TypeDTO type = new TypeDTO();
        type.setIdentifier("test");
        user.setType(type);
        user.setId(1);
        credential.setUser(user);
        when(repo.getUserType(1)).thenReturn("test");
        
        String result = repo.getUserType(1);

        assertEquals("test",result);
    }

    @Test
    void testSave() {
        CredentialDTO credential = new CredentialDTO();
        when(repo.save(credential)).thenReturn(credential);

        CredentialDTO savedCredential = repo.save(credential);

        assertEquals(credential, savedCredential);
    }
}
