package com.nmatute.octoger.usermanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmatute.octoger.usermanagement.domain.dto.EntityDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.domain.service.EntityService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/platform/user")
@NoArgsConstructor
public class Controller {
    
    private EntityService entityService;

    @PostMapping(value = {"/create", "/update"})
    public EntityDTO saveUser(@RequestBody EntityDTO entity){
        
        entityService.getUserService().save(entity.getUser());
        entityService.getCredentialService().save(entity.getCredential());
        
        return entity;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        return entityService.getUserService().getAll();
    }
    
    @GetMapping("/{userId}")
    public Optional<UserDTO> getUser(@PathVariable("userId") int userId){
        return entityService.getUserService().getById(userId);
    }
    
    @PostMapping("/delete/{userId}")
    public boolean deleteUser(@PathVariable("userId") int userId){
        
        if (entityService.getUserService().findUser(userId)) {
            entityService.getUserService().delete(userId);
            return true;
        } 

        return false;
    }

}
