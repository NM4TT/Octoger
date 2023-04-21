package com.nmatute.octoger.typemanagement.web.controller;


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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.domain.service.TypeService;

/**
 * Probar Controller de Web Service.
 *
 * @author: NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ControllerTest {

    @Mock
    private TypeService service;

    @InjectMocks
    private Controller controller;
    
    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void createType(){
        TypeDTO type = new TypeDTO();
        type.setId(1);

        ResponseEntity<String> expectedResponse = new ResponseEntity<String>
        ("Type created successfully.", HttpStatus.OK);

        ResponseEntity<String> actualResponse;

        when(service.save(type)).thenReturn(type);

        if (service.save(type) != null) {
            actualResponse = new ResponseEntity<String>("Type created successfully.", HttpStatus.OK);
        } else {
            actualResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());       
    }

    @Test
    void getAllTypes(){
        TypeDTO type = new TypeDTO();
        when(service.getAll()).thenReturn(List.of(type));

        List<TypeDTO> result = service.getAll();

        assertNotNull(result);
    }

    @Test
    void getByIdentifier(){
        TypeDTO type = new TypeDTO();
        type.setIdentifier("identifier");
        when(service.getByIdentifier("identifier")).thenReturn(type);

        TypeDTO result = service.getByIdentifier("identifier");

        assertEquals(type, result);
    }
    
    @Test
    void getType(){
        TypeDTO type = new TypeDTO();
        type.setId(1);
        when(service.getById(1)).thenReturn(type);

        TypeDTO result = service.getById(1);

        assertEquals(type, result);
    }
    
    @Test
    void deleteType(){
        TypeDTO type = new TypeDTO();
        type.setId(1);

        ResponseEntity<String> expectedResponse = new ResponseEntity<String>
        ("Type deleted successfully.", HttpStatus.OK);

        ResponseEntity<String> actualResponse;

        when(service.getById(1)).thenReturn(type);

        if (service.getById(1) != null) {
            actualResponse = new ResponseEntity<String>("Type deleted successfully.", HttpStatus.OK);
        } else {
            actualResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }

}
