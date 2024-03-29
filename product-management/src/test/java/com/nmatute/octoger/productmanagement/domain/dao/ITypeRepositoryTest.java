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

import com.nmatute.octoger.productmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.ITypeCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.productmanagement.persistence.repository.TypeRepository;

/**
 * Clase para testear Repositorio de Tipos en web service.
 * 
 * @author NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ITypeRepositoryTest {
    
    @Mock
    private ITypeCrudRepository crud;
    @Mock
    private TypeMapper mapper;
    @InjectMocks
    private TypeRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void getByIdentifier(){
        TypeDTO type = new TypeDTO();
        type.setIdentifier("identifier");
        when(repo.getByIdentifier("identifier")).thenReturn(type);

        TypeDTO typeFound = repo.getByIdentifier("identifier");

        assertEquals(type, typeFound);
    }

}
