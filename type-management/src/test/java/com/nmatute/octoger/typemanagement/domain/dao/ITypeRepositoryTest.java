package com.nmatute.octoger.typemanagement.domain.dao;

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

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.persistence.crud.ITypeCrudRepository;
import com.nmatute.octoger.typemanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.typemanagement.persistence.repository.TypeRepository;

/**
 * Probar Repositorio de Tipos en Web Service.
 *
 * @author: NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ITypeRepositoryTest {

    @Mock
    private TypeMapper mapper;
    @Mock
    private ITypeCrudRepository crud;

    @InjectMocks
    private TypeRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save(){
        TypeDTO type = new TypeDTO();
        type.setId(1);
        when(repo.save(type)).thenReturn(type);

        TypeDTO savedType = repo.save(type);

        assertEquals(type, savedType);       
    }

    @Test
    void getAll(){
        TypeDTO type = new TypeDTO();
        when(repo.getAll()).thenReturn(List.of(type));

        List<TypeDTO> result = repo.getAll();

        assertNotNull(result);
    }

    @Test
    void getByIdentifier(){
        TypeDTO type = new TypeDTO();
        type.setIdentifier("identifier");
        when(repo.getByIdentifier("identifier")).thenReturn(type);

        TypeDTO result = repo.getByIdentifier("identifier");

        assertEquals(type, result);
    }
    
    @Test
    void getById(){
        TypeDTO type = new TypeDTO();
        type.setId(1);
        when(repo.getById(1)).thenReturn(type);

        TypeDTO result = repo.getById(1);

        assertEquals(type, result);
    }
    
}
