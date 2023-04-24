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

import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.mapper.UserMapper;
import com.nmatute.octoger.productmanagement.persistence.repository.UserRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class IUserRepositoryTest {
    
    @Mock
    private IUserCrudRepository crud;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById(){
        UserDTO user = new UserDTO();
        user.setId(1);
        when(repo.getById(1)).thenReturn(user);

        UserDTO userFound = repo.getById(1);
        
        assertEquals(user, userFound);
    }

}
