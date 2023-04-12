package com.nmatute.octoger.typemanagement.web.controller;

import java.util.List;

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

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.domain.service.TypeService;
import com.nmatute.octoger.typemanagement.web.security.auth.CreateRequest;
import com.nmatute.octoger.typemanagement.web.security.auth.UpdateRequest;
import com.nmatute.octoger.typemanagement.web.security.config.AdminEndpoint;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class Controller {
    
    private final TypeService typeService;
    private final Logger log = LoggerFactory.getLogger(Controller.class);

    @AdminEndpoint
    @PostMapping("/create")
    public ResponseEntity<String> createType(@RequestBody CreateRequest request){
        log.debug("Got /type/create");
        
        if (!request.isEmpty(request.getIdentifier()) &&
            !request.isEmpty(request.getDescription())
        ) {
        
        TypeDTO type = new TypeDTO();

        type.setIdentifier(request.getIdentifier());
        type.setDescription(request.getDescription());

        typeService.save(type);

        return new ResponseEntity<>("Type created successfully.", HttpStatus.OK); 
    }
    
    return new ResponseEntity<>("Type not created.", HttpStatus.BAD_REQUEST);        
}

    @AdminEndpoint
    @PutMapping("/update")
    public ResponseEntity<String> updateType(@RequestBody UpdateRequest request) {

        log.debug("Got PUT /type/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(request.getIdentifier()) &&
            !request.isEmpty(request.getDescription())
        ) {
            
            TypeDTO type = typeService.getById(request.getId()).get();

            type.setIdentifier(request.getIdentifier());
            type.setDescription(request.getDescription());
            typeService.save(type);

            return new ResponseEntity<>("Type updated successfully.", HttpStatus.OK); 
        }
        
        return new ResponseEntity<>("Type not updated.", HttpStatus.BAD_REQUEST);        
    }

    @AdminEndpoint
    @GetMapping("/all")
    public ResponseEntity<List<TypeDTO>> getAllTypes(){
        log.debug("Got type/all");
        return new ResponseEntity<>(typeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/identifier/{prefix}")
    public ResponseEntity<List<TypeDTO>> getByIdentifier(@PathVariable("prefix") String prefix){
        log.debug("Got type/identifier/{prefix}");
        
        if (prefix != null && !prefix.equals("")) {
            
            return typeService.getByPrefix(prefix)
            .map(types -> new ResponseEntity<>(types,HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @AdminEndpoint
    @GetMapping("/{typeId}")
    public ResponseEntity<TypeDTO> getType(@PathVariable("typeId") int typeId){
        log.debug("Got /user/" + typeId);
        
        if (typeId >= 0) {
            
            return typeService.getById(typeId)
            .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @AdminEndpoint
    @PostMapping("/delete/{typeId}")
    public ResponseEntity<String> deleteType(@PathVariable("typeId") int typeId){
        log.debug("Got /type/delete/" + typeId);
        if (typeService.getById(typeId).isPresent()) {
            typeService.delete(typeId);
            return new ResponseEntity<>("Type deleted successfully.", HttpStatus.OK);
        } 

        return new ResponseEntity<>("Type not found.", HttpStatus.NOT_FOUND);
    }

}
