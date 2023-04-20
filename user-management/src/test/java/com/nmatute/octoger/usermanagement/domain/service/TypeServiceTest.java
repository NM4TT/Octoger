package com.nmatute.octoger.usermanagement.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.TypeRepository;

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
    void testGetByIdentifier() {
        TypeDTO type = new TypeDTO();
        type.setIdentifier("TEST");
        type.setDescription("Esto es un test");
        when(service.getByIdentifier("TEST")).thenReturn(type);

        TypeDTO result = service.getByIdentifier("TEST");

        assertEquals(type, result);
    }
}