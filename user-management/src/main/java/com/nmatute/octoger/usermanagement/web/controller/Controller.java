package com.nmatute.octoger.usermanagement.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.domain.service.AuthenticationService;
import com.nmatute.octoger.usermanagement.domain.service.UserService;
import com.nmatute.octoger.usermanagement.web.security.auth.AuthenticationRequest;
import com.nmatute.octoger.usermanagement.web.security.auth.AuthenticationResponse;
import com.nmatute.octoger.usermanagement.web.security.auth.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class Controller {
    
    private final UserService userService;
    private final AuthenticationService authService;
    private final Logger log = LoggerFactory.getLogger(Controller.class);

    @PostMapping(value = {"/create", "/update"})
    public ResponseEntity<AuthenticationResponse> saveUser(@RequestBody RegisterRequest entity){
        log.debug("Got user/create or user/update");

        AuthenticationResponse response = authService.register(entity);

        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        log.debug("Got user/all");
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") int userId){
        log.debug("Got /user/" + userId);
        return userService.getById(userId)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/delete/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") int userId){
        log.debug("Got /user/delete/" + userId);
        if (userService.findUser(userId)) {
            userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } 

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> deleteUser(@RequestBody AuthenticationRequest request){
        log.debug("Got /authenticate");
        
        AuthenticationResponse response = authService.authenticate(request);

        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

}
