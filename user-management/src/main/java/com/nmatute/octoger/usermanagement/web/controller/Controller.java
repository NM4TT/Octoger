package com.nmatute.octoger.usermanagement.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO.Role;
import com.nmatute.octoger.usermanagement.domain.service.AuthenticationService;
import com.nmatute.octoger.usermanagement.domain.service.CredentialService;
import com.nmatute.octoger.usermanagement.domain.service.TypeService;
import com.nmatute.octoger.usermanagement.domain.service.UserService;
import com.nmatute.octoger.usermanagement.web.json.AuthenticationRequest;
import com.nmatute.octoger.usermanagement.web.json.AuthenticationResponse;
import com.nmatute.octoger.usermanagement.web.json.RegisterRequest;
import com.nmatute.octoger.usermanagement.web.json.UpdateRequest;
import com.nmatute.octoger.usermanagement.web.security.config.AdminEndpoint;

import lombok.RequiredArgsConstructor;

/**
 * Controller de Web Service
 * 
 * @author NM4TT
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class Controller {
    
    private final CredentialService credentialService;
    private final UserService userService;
    private final AuthenticationService authService;
    private final TypeService typeService;
    private final PasswordEncoder encoder;
    private final Logger log = LoggerFactory.getLogger(Controller.class);

    /**
     * Metodo para dar de alta usuarios.
     * @param entity Usuario a ser registrado en el sistema.
     * @return Token JWT.
     */
    @PostMapping("/public/create")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody RegisterRequest entity){
        log.debug("Got user/create");

        AuthenticationResponse response = authService.register(entity);

        return (response != null) ? 
        ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

    /**
     * Metodo para actualizar usuarios.
     * @param request Json con detalles de actualizacion.
     * @return Mensaje descriptivo de operacion realizada.
     */
    @AdminEndpoint
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UpdateRequest request) {

        log.debug("Got PUT /user/update");
        
        if (request.isEmpty(String.valueOf(request.getId())) &&
            request.isEmpty(request.getName()) &&
            request.isEmpty(request.getLastname()) &&
            request.isEmpty(request.getPersonalIdentifier()) &&
            request.isEmpty(request.getType()) && request.getType().startsWith("USR") &&
            request.isEmpty(request.getUsername()) &&
            request.isEmpty(request.getPassword())
        ){
            return new ResponseEntity<>("Request is empty.", HttpStatus.BAD_REQUEST);
        } else {
            
            try {
                UserDTO user = userService.findById(request.getId());
                CredentialDTO credential = credentialService.findByUsername(request.getUsername());
                
                if (user == null || credential == null) {
                    throw new Exception("Some DTOs were not found.");
                }
                
                user.setName(request.getName());
                user.setLastname(request.getLastname());
                user.setPersonalIdentifier(request.getPersonalIdentifier());
                user.setType(typeService.getByIdentifier(request.getType()));
                log.debug("User data recolected for update.");
                user = userService.save(user);
                log.debug("User updated.");

                credential.setUsername(request.getUsername());
                credential.setRole((user.getType().getIdentifier().endsWith("00") ? Role.ADMIN : Role.REGULAR));
                credential.setPassword(request.getPassword());
                credentialService.save(credential);    

                return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
                
            } catch (Exception e) {
                log.debug(e.getMessage());
            }

            return new ResponseEntity<>("User not updated.", HttpStatus.BAD_REQUEST);
        }
        
    }

    /**
     * Metodo para obtener todos los usuarios del sistema.
     * @return Lista de todos los usuarios.
     */
    @AdminEndpoint
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        log.debug("Got user/all");

        List<UserDTO> users = userService.getAll();

        return new ResponseEntity<>(users, 
                                    (users != null) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    /**
     * Metodo para obtener un usuario especifico por su ID.
     * @param userId ID del usuario.
     * @return Usuario encontrado.
     */
    @AdminEndpoint
    @GetMapping("get/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") long userId){
        log.debug("Got /user/" + userId);

        UserDTO user = userService.getById(userId);

        return new ResponseEntity<>(user, 
                                    (user != null) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    /**
     * Metodo para eliminar un usuario por su ID.
     * @param userId ID de usuario.
     * @return Mensaje descriptivo de operacion realizada.
     */
    @AdminEndpoint
    @PostMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") long userId){
        log.debug("Got /user/delete/" + userId);
        if (userService.findUser(userId)) {
            userService.delete(userId);
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        } 

        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para autenticar un usuario y generar un JWT token.
     * @param request Json con detalles de autenticacion.
     * @return Token JWT
     */
    @PostMapping("/public/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        log.debug("Got /authenticate");
        
        AuthenticationResponse response = authService.authenticate(request);

        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/public/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthenticationRequest request){

        CredentialDTO credential = null;
        HttpStatus status = HttpStatus.NOT_FOUND;

        if (!request.isEmpty(request.getUsername()) && !request.isEmpty(request.getPassword())) {
            
            credential = credentialService.findByUsername(request.getUsername());

            if (credential != null) {
                
                if (credential.getPassword().equals(encoder.encode(request.getPassword()))) {
                    status = HttpStatus.OK;
                    return new ResponseEntity<UserDTO>(credential.getUser(),status);
                } else {
                    return new ResponseEntity<UserDTO>(status);
                }

            } else {
                return new ResponseEntity<UserDTO>(status);
            }

        } else {
            return new ResponseEntity<UserDTO>(status);
        }

    }

}
