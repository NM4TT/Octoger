package com.nmatute.octoger.productmanagement.web.controller;

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

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;
import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.productmanagement.domain.service.ProductCollectionService;
import com.nmatute.octoger.productmanagement.domain.service.ProductService;
import com.nmatute.octoger.productmanagement.domain.service.UserService;
import com.nmatute.octoger.productmanagement.web.json.CreateProductCollectionRequest;
import com.nmatute.octoger.productmanagement.web.json.CreateProductRequest;
import com.nmatute.octoger.productmanagement.web.json.UpdateProductCollectionRequest;
import com.nmatute.octoger.productmanagement.web.json.UpdateProductRequest;
import com.nmatute.octoger.productmanagement.web.security.config.AdminEndpoint;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class Controller {
    
    private final Logger log = LoggerFactory.getLogger(Controller.class);
    private final ProductService productService;
    private final ProductCollectionService collectionService;
    private final UserService userService;
    
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request){
        log.debug("Got /product/create");
        
        if (!request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getPrice())) &&
            !request.isEmpty(String.valueOf(request.getBenefit()))
        ) {
        
            ProductDTO product = new ProductDTO();
            ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
            product.setProductCollection(collection);
            product.setPrice(request.getPrice());
            product.setBenefit(request.getBenefit());
            product.setAvailable(true);
            productService.save(product);
            log.debug("Product saved successfully.");

            return new ResponseEntity<>("Product created successfully.\n" + product.toString(), HttpStatus.OK);
        } 
        return new ResponseEntity<>("Product not created.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/collection/create")
    public ResponseEntity<String> createProductCollection(@RequestBody CreateProductCollectionRequest request){
        log.debug("Got /product/collection/create");
        
        if (!request.isEmpty(request.getDescription()) &&
            !request.isEmpty(request.getProvider()) &&
            !request.isEmpty(String.valueOf(request.getCost())) &&
            !request.isEmpty(String.valueOf(request.getUserId())) &&
            !request.isEmpty(String.valueOf(request.getProductQuantity()))
        ) {
        
            ProductCollectionDTO collection = new ProductCollectionDTO();
            UserDTO user = userService.getById(request.getUserId());

            collection.setUser(user);
            collection.setDescription(request.getDescription());
            collection.setProvider(request.getProvider());
            collection.setCost(request.getCost());
            collection.setProductQuantity(request.getProductQuantity());

            collectionService.save(collection);
            log.debug("Product Collection saved successfully.");

            return new ResponseEntity<>("ProductCollection created successfully.", HttpStatus.OK); 
        }
    
        return new ResponseEntity<>("ProductCollection not created.", HttpStatus.BAD_REQUEST);        
    }

    
    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductRequest request) {

        log.debug("Got PUT /product/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getPrice())) &&
            !request.isEmpty(String.valueOf(request.getBenefit())) &&
            !request.isEmpty(String.valueOf(request.isAvailable()))
        ) {
            
            ProductDTO product = productService.getById(request.getId());
            ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
            product.setProductCollection(collection);
            product.setPrice(request.getPrice());
            product.setBenefit(request.getBenefit());
            product.setAvailable(request.isAvailable());

            productService.save(product);
            log.debug("Product updated successfully.");
            
            return new ResponseEntity<>("Product updated successfully.", HttpStatus.OK); 
        }
        
        return new ResponseEntity<>("Product not updated.", HttpStatus.BAD_REQUEST);        
    }

    @PutMapping("/collection/update")
    public ResponseEntity<String> updateProductCollection(@RequestBody UpdateProductCollectionRequest request) {

        log.debug("Got PUT /product/collection/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(request.getDescription()) &&
            !request.isEmpty(request.getProvider()) &&
            !request.isEmpty(String.valueOf(request.getCost())) &&
            !request.isEmpty(String.valueOf(request.getUserId())) &&
            !request.isEmpty(String.valueOf(request.getProductQuantity()))
        ) {
            
            ProductCollectionDTO collection = collectionService.getById(request.getId());
            UserDTO user = userService.getById(request.getUserId());

            collection.setUser(user);
            collection.setDescription(request.getDescription());
            collection.setProvider(request.getProvider());
            collection.setCost(request.getCost());
            collection.setProductQuantity(request.getProductQuantity());

            collectionService.save(collection);
            log.debug("Product Collection updated successfully.");
            
            return new ResponseEntity<>("ProductCollection updated successfully.", HttpStatus.OK); 
        }
        
        return new ResponseEntity<>("ProductCollection not updated.", HttpStatus.BAD_REQUEST);        
    }

    @PutMapping("/collection/{collectionId}/setNonAvailable")
    public ResponseEntity<String> setNonAvailableProductsByCollection(@PathVariable("collectionId") int collectionId) {

        log.debug("Got PUT /product/collection/{collectionId}/setNonAvailable");
        
        List<ProductDTO> products = productService.getByCollection(collectionService.getById(collectionId));

        for (ProductDTO productDTO : products) {
            productDTO.setAvailable(false);
            productService.save(productDTO);
        }
        
        return new ResponseEntity<>("Products updated.", HttpStatus.OK);        
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        log.debug("Got product/all");
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/collection/all")
    public ResponseEntity<List<ProductCollectionDTO>> getAllProductCollections(){
        log.debug("Got product/collection/all");
        return new ResponseEntity<>(collectionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") int productId){
        log.debug("Got product/" + productId);
        return new ResponseEntity<>(productService.getById(productId),HttpStatus.OK);
    }

    @GetMapping("/collection/get/{collectionId}")
    public ResponseEntity<ProductCollectionDTO> getProductCollectionById(@PathVariable("collectionId") int collectionId){
        log.debug("Got prpduct/collection/" + collectionId);

        ProductCollectionDTO collection = collectionService.getById(collectionId);

        return new ResponseEntity<>(collection,HttpStatus.OK);
    } 

    @GetMapping("/collection/{collectionId}/list")
    public ResponseEntity<List<ProductDTO>> getProductsOfProductCollection(@PathVariable("collectionId") int collectionId){
        log.debug("Got product/collection/{collectionId}/list" + collectionId);

        ProductCollectionDTO collection = collectionService.getById(collectionId);

        return new ResponseEntity<>(productService.getByCollection(collection),HttpStatus.OK);
    } 
    
    @GetMapping("/collection/responsible/{userId}")
    public ResponseEntity<List<ProductCollectionDTO>> getProductCollectionsOfUser(@PathVariable("userId") int userId){
        log.debug("Got product/collection/all");
        UserDTO user = userService.getById(userId);
        return new ResponseEntity<>(collectionService.getByResponsible(user), HttpStatus.OK);
    }

    @AdminEndpoint
    @PostMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId){
        log.debug("Got /product/delete/" + productId);
        
        Optional<ProductDTO> optionalProduct = Optional.of(productService.getById(productId));

        if (optionalProduct.isPresent()) {
            productService.delete(productId);
            return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Product not found.", HttpStatus.NOT_FOUND);
    }

    @AdminEndpoint
    @PostMapping("/collection/delete/{collectionId}")
    public ResponseEntity<String> deleteProductCollection(@PathVariable("collectionId") int collectionId){
        log.debug("Got /product/collection/delete/" + collectionId);
        
        Optional<ProductCollectionDTO> optionalCollection = Optional.of(collectionService.getById(collectionId));

        if (optionalCollection.isPresent()) {
            collectionService.delete(collectionId);
            return new ResponseEntity<>("Product Collection deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Product Collection not found.", HttpStatus.NOT_FOUND);
    }

}
