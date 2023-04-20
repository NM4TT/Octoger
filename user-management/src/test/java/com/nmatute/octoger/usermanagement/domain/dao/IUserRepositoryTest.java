package com.nmatute.octoger.usermanagement.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

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
import com.nmatute.octoger.usermanagement.persistence.entity.Type;
import com.nmatute.octoger.usermanagement.persistence.entity.User;
import com.nmatute.octoger.usermanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.usermanagement.persistence.mapper.UserMapper;
import com.nmatute.octoger.usermanagement.persistence.repository.UserRepository;

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

    private User user;
    private UserDTO userDTO;
    private Type type;
    private TypeDTO typeDTO;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setName("name");
        user.setLastname("lastname");
        
        userDTO = new UserDTO();
        userDTO.setName("name");
        userDTO.setLastname("lastname");

        typeDTO = new TypeDTO();
        type = new Type();
    }

    @Test
    void testFindUser() {
        when(crud.findUser(anyInt())).thenReturn(Integer.MIN_VALUE);

        int value = crud.findUser(1);

        assertEquals(value, Integer.MIN_VALUE);
    }

    @Test
    void testGetAll() {
        when(mapper.toUserDTO(user)).thenReturn(userDTO);
        when(crud.findAll()).thenReturn(List.of(user));

        List<UserDTO> users = mapper.toUserDTOs((List<User>) crud.findAll());

        assertNotNull(users);
    }

    @Test
    void testGetById() {
        when(mapper.toUserDTO(user)).thenReturn(userDTO);
        when(crud.findById(any(Integer.class))).thenReturn(Optional.of(user));

        UserDTO result = mapper.toUserDTO(crud.findById(1).get());

        assertNotNull(result);
    }

    @Test
    void testGetByType() {
        when(mapper.toUserDTO(user)).thenReturn(userDTO);
        when(typeMapper.toType(typeDTO)).thenReturn(type);
        when(crud.findByType(any(Type.class))).thenReturn(List.of(user));

        List<UserDTO> users = mapper.toUserDTOs((List<User>) crud.findByType(type));

        assertNotNull(users);
    }

    @Test
    void testSave() {
        when(mapper.toUserDTO(user)).thenReturn(userDTO);
        when(crud.save(any(User.class))).thenReturn(user);

        UserDTO result = mapper.toUserDTO(crud.save(user));

        assertNotNull(result);
    }
}
