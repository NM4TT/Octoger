package com.nmatute.octoger.usermanagement.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.UserRepository;

/**
 * Probar UserService del Web Service.
 *
 * @author: NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {
    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUser() {
        UserDTO user = new UserDTO();
        user.setId(1);
        when(service.findUser(1)).thenReturn(true);

        boolean found = service.findUser(1);

        assertEquals(true, found);
    }

    @Test
    void testGetAll() {
        UserDTO user = new UserDTO();
        when(service.getAll()).thenReturn(List.of(user));

        List<UserDTO> users = service.getAll();

        assertNotNull(users);
    }

    @Test
    void testGetById() {
        UserDTO user = new UserDTO();
        user.setId(1);
        when(service.getById(1)).thenReturn(user);

        UserDTO result = service.getById(1);

        assertEquals(user, result);
    }

    @Test
    void testGetByType() {
        UserDTO user = new UserDTO();
        TypeDTO type = new TypeDTO();
        user.setType(type);
        when(service.getByType(type)).thenReturn(List.of(user));

        List<UserDTO> users = service.getByType(type);

        assertNotNull(users);
    }

    @Test
    void testSave() {
        UserDTO user = new UserDTO();
        when(service.save(user)).thenReturn(user);

        UserDTO result = service.save(user);

        assertEquals(user, result);
    }
}
