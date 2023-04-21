package com.nmatute.octoger.usermanagement.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
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

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.domain.service.AuthenticationService;
import com.nmatute.octoger.usermanagement.domain.service.CredentialService;
import com.nmatute.octoger.usermanagement.domain.service.UserService;
import com.nmatute.octoger.usermanagement.web.json.AuthenticationRequest;
import com.nmatute.octoger.usermanagement.web.json.AuthenticationResponse;
import com.nmatute.octoger.usermanagement.web.json.RegisterRequest;

/**
 * Probar Controller de Web Service.
 *
 * @author: NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ControllerTest {

    @Mock
    private CredentialService credentialService;
    @Mock
    private UserService userService;
    @Mock
    private AuthenticationService authService;

    @InjectMocks
    private Controller controller;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Probar metodo para crear usuarios en controller.
     */
    @Test
    void testCreateUser() {
        RegisterRequest request = new RegisterRequest();
        AuthenticationResponse authResponse = new AuthenticationResponse();
        ResponseEntity<AuthenticationResponse> expectedResponse = 
        new ResponseEntity<AuthenticationResponse>(authResponse, HttpStatus.OK);
        ResponseEntity<AuthenticationResponse> actualResponse;
        when(authService.register(request)).thenReturn(authResponse);
        
        AuthenticationResponse actualAuthResponse = authService.register(request);

        if (actualAuthResponse != null) {
            actualResponse = new ResponseEntity<AuthenticationResponse>(actualAuthResponse, HttpStatus.OK);
        } else {
            actualResponse = new ResponseEntity<AuthenticationResponse>(HttpStatus.BAD_REQUEST);
        }

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }

    /**
     * Probar metodo para eliminar usuario en controller.
     */
    @Test
    void testDeleteUser() {
        UserDTO user = new UserDTO();
        user.setId(1);

        ResponseEntity<String> expectedResponse = new ResponseEntity<String>("User deleted successfully.", HttpStatus.OK);
        ResponseEntity<String> actualResponse;

        when(userService.findUser(1)).thenReturn(true);

        if (userService.findUser(1)) {
            actualResponse = new ResponseEntity<String>("User deleted successfully.", HttpStatus.OK);
        } else {
            actualResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }

    /**
     * Probar metodo para autenticacion de usuario en controller.
     */
    @Test
    void testAuthenticate() {
        AuthenticationRequest request = new AuthenticationRequest();
        AuthenticationResponse authResponse = new AuthenticationResponse();
        ResponseEntity<AuthenticationResponse> expectedResponse = 
        new ResponseEntity<AuthenticationResponse>(authResponse, HttpStatus.OK);
        ResponseEntity<AuthenticationResponse> actualResponse;
        when(authService.authenticate(request)).thenReturn(authResponse);
        
        AuthenticationResponse actualAuthResponse = authService.authenticate(request);

        if (actualAuthResponse != null) {
            actualResponse = new ResponseEntity<AuthenticationResponse>(actualAuthResponse, HttpStatus.OK);
        } else {
            actualResponse = new ResponseEntity<AuthenticationResponse>(HttpStatus.BAD_REQUEST);
        }

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }

    /**
     * Probar metodo para obtener todos los usuarios.
     */
    @Test
    void testGetAllUsers() {
        UserDTO user = new UserDTO();
        when(userService.getAll()).thenReturn(List.of(user));

        List<UserDTO> result = userService.getAll();

        assertNotNull(result);
    }

    /**
     * Probar metodo para obtener usuario especifico.
     */
    @Test
    void testGetUser() {
        UserDTO user = new UserDTO();
        user.setId(1);
        when(userService.getById(1)).thenReturn(user);

        UserDTO result = userService.getById(1);

        assertEquals(user, result);
    }

    /**
     * Probar metodo para actualizar usuario en controller.
     */
    @Test
    void testUpdateUser() {
        UserDTO user = new UserDTO();
        CredentialDTO credential = new CredentialDTO();

        when(userService.save(user)).thenReturn(user);
        when(credentialService.save(credential)).thenReturn(credential);

        UserDTO savedUser = userService.save(user);
        CredentialDTO savedCredential = credentialService.save(credential);

        assertEquals(user, savedUser);
        assertEquals(credential, savedCredential);
    }

    /**
     * Metodo para probar login en Controller.
     * 
     * @author NM4TT
     */
    @Test
    void testLogin(){
        CredentialDTO credential = new CredentialDTO();
        UserDTO user = new UserDTO();
        credential.setUser(user);
        credential.setUsername("username");
        credential.setPassword("12345");
        when(credentialService.findByUsername("username")).thenReturn(credential);

        CredentialDTO result = credentialService.findByUsername("username");

        if (result != null) {
            
            if (result.getPassword().equals(credential.getPassword())) {
                assertEquals(credential, result);
            } else {
                fail("password no coincide.", null);
            }

        } else {
            fail("credencial no encontrada.", new NullPointerException());
        }
    }
}
