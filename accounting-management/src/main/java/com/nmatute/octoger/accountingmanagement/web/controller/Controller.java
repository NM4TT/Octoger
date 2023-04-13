package com.nmatute.octoger.accountingmanagement.web.controller;

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

import com.nmatute.octoger.accountingmanagement.web.json.CreateRequest;
import com.nmatute.octoger.accountingmanagement.web.json.UpdateRequest;
import com.nmatute.octoger.accountingmanagement.web.security.config.AdminEndpoint;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class Controller {
    
    private final Logger log = LoggerFactory.getLogger(Controller.class);

    @AdminEndpoint
    @PostMapping("/create")
    public ResponseEntity<String> createType(@RequestBody CreateRequest request){
        log.debug("Got /type/create");
        
        if (!request.isEmpty(request.getIdentifier()) &&
            !request.isEmpty(request.getDescription())
        ) {
        
        String type = new String();

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
            
            
            return new ResponseEntity<>("Type updated successfully.", HttpStatus.OK); 
        }
        
        return new ResponseEntity<>("Type not updated.", HttpStatus.BAD_REQUEST);        
    }

    @AdminEndpoint
    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllTypes(){
        log.debug("Got type/all");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/identifier/{prefix}")
    public ResponseEntity<List<String>> getByIdentifier(@PathVariable("prefix") String prefix){
        log.debug("Got type/identifier/{prefix}");
        
        
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @AdminEndpoint
    @GetMapping("/{typeId}")
    public ResponseEntity<String> getType(@PathVariable("typeId") int typeId){
        log.debug("Got /user/" + typeId);
        
       
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @AdminEndpoint
    @PostMapping("/delete/{typeId}")
    public ResponseEntity<String> deleteType(@PathVariable("typeId") int typeId){
        log.debug("Got /type/delete/" + typeId);
        

        return new ResponseEntity<>("Type not found.", HttpStatus.NOT_FOUND);
    }

}
