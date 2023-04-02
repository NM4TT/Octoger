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
import com.nmatute.octoger.usermanagement.domain.service.EntityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class Controller {
    
    private final EntityService entityService;

    @PostMapping(value = {"/create", "/update"})
    public ResponseEntity<EntityDTO> saveUser(@RequestBody EntityDTO entity){
        
        entityService.getUserService().save(entity.getUser());
        entityService.getCredentialService().save(entity.getCredential());
        
        return new ResponseEntity<>(entity, HttpStatus.OK);
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
