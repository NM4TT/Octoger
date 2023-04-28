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
import com.nmatute.octoger.typemanagement.web.json.CreateRequest;
import com.nmatute.octoger.typemanagement.web.json.UpdateRequest;
import com.nmatute.octoger.typemanagement.web.security.config.AdminEndpoint;

import lombok.RequiredArgsConstructor;

/**
 * Controller de Web Service
 * 
 * @author NM4TT
 */
@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class Controller {
    
    private final TypeService typeService;
    private final Logger log = LoggerFactory.getLogger(Controller.class);

    /**
     * Metodo para crear un tipo.
     * @param request informacion del tipo a crear
     * @return estatus de operacion
     */
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

    /**
     * Metodo para actualizar un tipo.
     * @param request detalles de actualizacion
     * @return estatus de operacion
     */
    @AdminEndpoint
    @PutMapping("/update")
    public ResponseEntity<String> updateType(@RequestBody UpdateRequest request) {

        log.debug("Got PUT /type/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(request.getIdentifier()) &&
            !request.isEmpty(request.getDescription())
        ) {
            
            TypeDTO type = typeService.getById(request.getId());

            type.setIdentifier(request.getIdentifier());
            type.setDescription(request.getDescription());
            typeService.save(type);

            return new ResponseEntity<>("Type updated successfully.", HttpStatus.OK); 
        }
        
        return new ResponseEntity<>("Type not updated.", HttpStatus.BAD_REQUEST);        
    }

    /**
     * Metodo para obtener lista de todos los tipos.
     * @return Lista de todos los tipos existentes
     */
    @GetMapping("/all")
    public ResponseEntity<List<TypeDTO>> getAllTypes(){
        log.debug("Got type/all");

        List<TypeDTO> types = typeService.getAll();

        return new ResponseEntity<>(types, 
                                    (types != null) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener una lista de tipos por su prefijo.
     * @param prefix prefijo de tipos
     * @return Lista de tipos con prefijo en comun
     */
    @GetMapping("/identifier/{prefix}")
    public ResponseEntity<List<TypeDTO>> getByIdentifier(@PathVariable("prefix") String prefix){
        log.debug("Got type/identifier/{prefix}");
        
        List<TypeDTO> types = typeService.getByPrefix(prefix);

        return new ResponseEntity<>(types, 
                                    (types != null && types.size() > 0) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    /**
     * Metodo para obtener tipo por ID.
     * @param typeId ID del tipo
     * @return Tipo
     */
    @GetMapping("/{typeId}")
    public ResponseEntity<TypeDTO> getType(@PathVariable("typeId") int typeId){
        log.debug("Got /user/" + typeId);
        
        TypeDTO type = typeService.getById(typeId);

        return new ResponseEntity<>(type, 
                                    (type != null) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    /**
     * Metodo para eliminar un tipo.
     * @param typeId ID del tipo
     * @return estatus de operacion
     */
    @AdminEndpoint
    @PostMapping("/delete/{typeId}")
    public ResponseEntity<String> deleteType(@PathVariable("typeId") int typeId){
        log.debug("Got /type/delete/" + typeId);

        TypeDTO type = typeService.getById(typeId);

        if (type != null) {
            typeService.delete(typeId);
            return new ResponseEntity<>("Type deleted successfully.", HttpStatus.OK);
        } 

        return new ResponseEntity<>("Type not found.", HttpStatus.NOT_FOUND);
    }

}
