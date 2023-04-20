package com.nmatute.octoger.usermanagement.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.persistence.crud.ITypeCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.entity.Type;
import com.nmatute.octoger.usermanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.usermanagement.persistence.repository.TypeRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class ITypeRepositoryTest {

    @Mock
    private ITypeCrudRepository crud;
    @Mock
    private TypeMapper mapper;

    @InjectMocks
    private TypeRepository repo;

    private TypeDTO typeDTO;
    private Type type;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);

        type = new Type();
        type.setIdentifier("TEST");
        type.setDescription("Esto es un test");
        
        typeDTO = new TypeDTO();
        typeDTO.setIdentifier("TEST");
        typeDTO.setDescription("Esto es un test");
    }

    @Test
    void testGetByIdentifier() {
        when(mapper.toTypeDTO(type)).thenReturn(typeDTO);
        when(crud.findByIdentifier(anyString())).thenReturn(type);

        TypeDTO result = mapper.toTypeDTO(crud.findByIdentifier("TEST"));

        assertEquals(type.getIdentifier(), result.getIdentifier());
        assertEquals(type.getDescription(), result.getDescription());
    }
}
