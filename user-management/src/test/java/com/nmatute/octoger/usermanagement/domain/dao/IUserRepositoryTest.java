package com.nmatute.octoger.usermanagement.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.usermanagement.persistence.mapper.UserMapper;
import com.nmatute.octoger.usermanagement.persistence.repository.UserRepository;

/**
 * Probar UserRepository del Web Service.
 *
 * @author: NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class IUserRepositoryTest {

    @Mock
    private IUserCrudRepository crud;
    @Mock
    private UserMapper mapper;
    @Mock
    private TypeMapper typeMapper;

    @InjectMocks
    private UserRepository repo;
    

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUser() {
        UserDTO user = new UserDTO();
        user.setId(1);
        when(crud.findUser(1)).thenReturn(1);

        int value = crud.findUser(1);

        assertEquals(1, value);
    }

    @Test
    void testGetAll() {
        UserDTO user = new UserDTO();
        when(repo.getAll()).thenReturn(List.of(user));

        List<UserDTO> users = repo.getAll();

        assertNotNull(users);
    }

    @Test
    void testGetById() {
        UserDTO user = new UserDTO();
        user.setId(1);
        when(repo.getById(1)).thenReturn(user);

        UserDTO result = repo.getById(1);

        assertEquals(user, result);
    }

    @Test
    void testGetByType() {
        UserDTO user = new UserDTO();
        TypeDTO type = new TypeDTO();
        user.setType(type);
        when(repo.getByType(type)).thenReturn(List.of(user));

        List<UserDTO> users = repo.getByType(type);

        assertNotNull(users);
    }

    @Test
    void testSave() {
        UserDTO user = new UserDTO();
        when(repo.save(user)).thenReturn(user);

        UserDTO result = repo.save(user);

        assertEquals(user, result);
    }
}
