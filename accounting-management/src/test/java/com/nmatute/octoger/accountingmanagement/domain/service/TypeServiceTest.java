package com.nmatute.octoger.accountingmanagement.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.TypeRepository;

/**
 * Clase para testear Servicio de Tipos.
 * 
 * @author NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class TypeServiceTest {

    @Mock
    private TypeRepository repo;
    @InjectMocks
    private TypeService service;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByIdentifier(){
        TypeDTO type = new TypeDTO();
        type.setIdentifier("identifier");
        when(service.getByIdentifier("identifier")).thenReturn(type);

        TypeDTO typeFound = service.getByIdentifier("identifier");

        assertEquals(type, typeFound);
    }

}
