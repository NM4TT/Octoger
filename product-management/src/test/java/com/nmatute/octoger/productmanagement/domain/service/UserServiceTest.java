package com.nmatute.octoger.productmanagement.domain.service;

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
import com.nmatute.octoger.productmanagement.persistence.repository.UserRepository;

/**
 * Clase para testear Servicio de Usuarios en web service.
 * 
 * @author NM4TT
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
    void getById(){
        UserDTO user = new UserDTO();
        user.setId(1);
        when(service.getById(1)).thenReturn(user);

        UserDTO userFound = service.getById(1);

        assertEquals(user, userFound);
    }
}
