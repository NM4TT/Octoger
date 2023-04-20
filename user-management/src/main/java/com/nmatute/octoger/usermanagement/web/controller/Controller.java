package com.nmatute.octoger.usermanagement.web.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class Controller {
    
    private final CredentialService credentialService;
    private final UserService userService;
    private final AuthenticationService authService;
    private final TypeService typeService;
    private final Logger log = LoggerFactory.getLogger(Controller.class);

    @AdminEndpoint
    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody RegisterRequest entity){
        log.debug("Got user/create");

        AuthenticationResponse response = authService.register(entity);

        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

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
            
            // Retrieve the existing entity from the repository by its ID
            Optional<UserDTO> optionalUser = Optional.of(userService.findById(request.getId()));
            Optional<CredentialDTO> optionalCredential = Optional.of(credentialService.findByUsername(request.getUsername()));
            
            if (!optionalUser.isPresent() || !optionalCredential.isPresent()) {
                return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
            }
            
            UserDTO existingUser  = optionalUser.get();
            CredentialDTO existingCredential = optionalCredential.get();
            
            existingUser.setName(request.getName());
            existingUser.setLastname(request.getLastname());
            existingUser.setPersonalIdentifier(request.getPersonalIdentifier());
            existingUser.setType(typeService.getByIdentifier(request.getType()));
            log.debug("User data recolected for update.");
            existingUser = userService.save(existingUser);
            log.debug("User updated.");

            existingCredential.setUsername(request.getUsername());
            existingCredential.setRole((existingUser.getType().getIdentifier().endsWith("00") ? Role.ADMIN : Role.REGULAR));
            existingCredential.setPassword(request.getPassword());
            credentialService.save(existingCredential);

            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
        }
        
    }

    @AdminEndpoint
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        log.debug("Got user/all");
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    
    @AdminEndpoint
    @GetMapping("get/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") int userId){
        log.debug("Got /user/" + userId);

        UserDTO user = userService.getById(userId);

        return (user != null) ?
        new ResponseEntity<>(user, HttpStatus.OK) :
        new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
    
    @AdminEndpoint
    @PostMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId){
        log.debug("Got /user/delete/" + userId);
        if (userService.findUser(userId)) {
            userService.delete(userId);
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        } 

        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        log.debug("Got /authenticate");
        
        AuthenticationResponse response = authService.authenticate(request);

        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

}
