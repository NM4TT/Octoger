package com.nmatute.octoger.usermanagement.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.web.json.TypeResponse;

@Service
public class TypeService {

    private final Logger log = LoggerFactory.getLogger(TypeService.class);
    
    public TypeDTO getByIdentifier(String identifier){
        TypeDTO type = null;

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9011/type/public/identifier/" + identifier;

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        
        // create an instance of the ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON string to be parsed
        if (response != null) {
            String jsonString = response.getBody().replace("[", "").replace("]", "");
        
            // parse the JSON string into a Java object
            TypeResponse responseObject;
            try {
                responseObject = objectMapper.readValue(jsonString, TypeResponse.class);
            
                if (responseObject != null &&
                !responseObject.isEmpty(String.valueOf(responseObject.getId())) &&
                !responseObject.isEmpty(responseObject.getIdentifier()) &&
                !responseObject.isEmpty(responseObject.getDescription())
                ) { 
                    type = new TypeDTO();
                    type.setId(responseObject.getId());
                    type.setIdentifier(responseObject.getIdentifier());
                    type.setDescription(responseObject.getDescription());
                }

            } catch (JsonProcessingException e) {
                log.error("ERROR AL PARSEAR JSON: " + e.getMessage());
            }
        
        }

        return type;
    }

}
