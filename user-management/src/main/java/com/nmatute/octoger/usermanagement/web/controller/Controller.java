package com.nmatute.octoger.usermanagement.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmatute.octoger.usermanagement.domain.dto.EntityDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.domain.service.AuthenticationService;
import com.nmatute.octoger.usermanagement.domain.service.EntityService;
import com.nmatute.octoger.usermanagement.web.security.auth.AuthenticationResponse;
import com.nmatute.octoger.usermanagement.web.security.auth.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class Controller {
    
    private final EntityService entityService;
    private final AuthenticationService authService;

    @PostMapping(value = {"/create", "/update"})
    public ResponseEntity<AuthenticationResponse> saveUser(@RequestBody EntityDTO entity){
        
        return ResponseEntity.ok(
            authService.register(new RegisterRequest(
                entity.getUser().getName(), 
                entity.getUser().getLastname(),
                entity.getUser().getPersonalIdentifier(), 
                entity.getUser().getType(),
                entity.getCredential().getUsername(), 
                entity.getCredential().getPassword())
                )
        );
        
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(entityService.getUserService().getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") int userId){
        return entityService.getUserService().getById(userId)

        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") int userId){
        
        if (entityService.getUserService().findUser(userId)) {
            entityService.getUserService().delete(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } 

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
