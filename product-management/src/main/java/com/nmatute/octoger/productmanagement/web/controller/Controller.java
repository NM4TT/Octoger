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

/**
 * Controller para Web Service
 * 
 * @author NM4TT
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class Controller {
    
    private final Logger log = LoggerFactory.getLogger(Controller.class);
    private final ProductService productService;
    private final ProductCollectionService collectionService;
    private final UserService userService;
    
    /**
     * Metodo para crear productos.
     * @param request detalles del producto
     * @return estatus de operacion
     */
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request){
        log.debug("Got /product/create");
        
        if (!request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getPrice())) &&
            !request.isEmpty(String.valueOf(request.getBenefit()))
        ) {
        
            try {
                
                ProductDTO product = new ProductDTO();
                ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
                
                if(collection == null){
                    throw new Exception("Some DTOs were not found.");
                }
                
                product.setProductCollection(collection);
                product.setPrice(request.getPrice());
                product.setBenefit(request.getBenefit());
                product.setAvailable(true);
                productService.save(product);
                log.debug("Product saved successfully.");

                return new ResponseEntity<>("Product created successfully.", HttpStatus.OK);

            } catch (Exception e) {
                log.debug(e.getMessage());
            }
            
        } 
        return new ResponseEntity<>("Product not created.", HttpStatus.BAD_REQUEST);
    }

    /**
     * Metodo para crear coleccion de productos.
     * @param request detalles de coleccion
     * @return estatus de operacion
     */
    @PostMapping("/collection/create")
    public ResponseEntity<String> createProductCollection(@RequestBody CreateProductCollectionRequest request){
        log.debug("Got /product/collection/create");
        
        if (!request.isEmpty(request.getDescription()) &&
            !request.isEmpty(request.getProvider()) &&
            !request.isEmpty(String.valueOf(request.getCost())) &&
            !request.isEmpty(String.valueOf(request.getUserId())) &&
            !request.isEmpty(String.valueOf(request.getProductQuantity()))
        ) {
        
            try {
                ProductCollectionDTO collection = new ProductCollectionDTO();
                UserDTO user = userService.getById(request.getUserId());

                if (user == null) {
                    throw new Exception("Some DTOs were not found.");
                }

                collection.setUser(user);
                collection.setDescription(request.getDescription());
                collection.setProvider(request.getProvider());
                collection.setCost(request.getCost());
                collection.setProductQuantity(request.getProductQuantity());

                collectionService.save(collection);
                log.debug("Product Collection saved successfully.");

                return new ResponseEntity<>("ProductCollection created successfully.", HttpStatus.OK); 
            } catch (Exception e) {
                log.debug(e.getMessage());
            }
            
        }
    
        return new ResponseEntity<>("ProductCollection not created.", HttpStatus.BAD_REQUEST);        
    }

    
    /**
     * Metodo para actualizar producto.
     * @param request detalles de actualizacion
     * @return estatus de operacion
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductRequest request) {

        log.debug("Got PUT /product/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getPrice())) &&
            !request.isEmpty(String.valueOf(request.getBenefit())) &&
            !request.isEmpty(String.valueOf(request.isAvailable()))
        ) {
            
            try {
                ProductDTO product = productService.getById(request.getId());
                ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());

                if(product == null || collection == null){
                    throw new Exception("Some DTOs were not found.");
                }

                product.setProductCollection(collection);
                product.setPrice(request.getPrice());
                product.setBenefit(request.getBenefit());
                product.setAvailable(request.isAvailable());

                productService.save(product);
                log.debug("Product updated successfully.");
                
                return new ResponseEntity<>("Product updated successfully.", HttpStatus.OK); 
            } catch (Exception e) {
                log.debug(e.getMessage());
            }

        }
        
        return new ResponseEntity<>("Product not updated.", HttpStatus.BAD_REQUEST);        
    }

    /**
     * Metodo para actualizar coleccion.
     * @param request detalles de actualizacion
     * @return estatus de operacion
     */
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
            
            try {
                
                ProductCollectionDTO collection = collectionService.getById(request.getId());
                UserDTO user = userService.getById(request.getUserId());

                if (collection == null || user == null) {
                    throw new Exception("Some DTOs were not found.");
                }

                collection.setUser(user);
                collection.setDescription(request.getDescription());
                collection.setProvider(request.getProvider());
                collection.setCost(request.getCost());
                collection.setProductQuantity(request.getProductQuantity());

                collectionService.save(collection);
                log.debug("Product Collection updated successfully.");
                
                return new ResponseEntity<>("ProductCollection updated successfully.", HttpStatus.OK); 

            } catch (Exception e) {
                log.debug(e.getMessage());
            }

        }
        
        return new ResponseEntity<>("ProductCollection not updated.", HttpStatus.BAD_REQUEST);        
    }

    /**
     * Metodo para setear todos los productos de una coleccion como NoDisponibles.
     * @param collectionId ID de coleccion
     * @return estatus de operacion
     */
    @PutMapping("/collection/{collectionId}/setNonAvailable")
    public ResponseEntity<String> setNonAvailableProductsByCollection(@PathVariable("collectionId") int collectionId) {

        log.debug("Got PUT /product/collection/{collectionId}/setNonAvailable");
        
        try {
            ProductCollectionDTO collection = collectionService.getById(collectionId);
            List<ProductDTO> products = productService.getByCollection(collection);

            if (collection == null || products == null) {
                throw new Exception("Some DTOs were not found.");
            }

            for (ProductDTO productDTO : products) {
                productDTO.setAvailable(false);
                productService.save(productDTO);
            }

            return new ResponseEntity<>("Products updated.", HttpStatus.OK);

        } catch (Exception e) {
           log.debug(e.getMessage());
        }

        return new ResponseEntity<>("Products not updated.", HttpStatus.NOT_FOUND);        
    }

    /**
     * Metodo para obtener todos los productos en el sistema.
     * @return Lista de productos
     */
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        log.debug("Got product/all");

        List<ProductDTO> products = productService.getAll();

        return new ResponseEntity<>(products, 
                                    (products != null) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las colecciones de productos en sistema.
     * @return Lista de colecciones
     */
    @GetMapping("/collection/all")
    public ResponseEntity<List<ProductCollectionDTO>> getAllProductCollections(){
        log.debug("Got product/collection/all");

        List<ProductCollectionDTO> collections = collectionService.getAll();

        return new ResponseEntity<>(collections, 
                                    (collections != null) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener producto especifico por ID.
     * @param productId ID de producto
     * @return producto encontrado
     */
    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") int productId){
        log.debug("Got product/" + productId);

        ProductDTO product = productService.getById(productId);

        return new ResponseEntity<>(product, 
                                    (product != null) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener coleccion de producto especifica por ID.
     * @param collectionId ID de coleccion
     * @return coleccion encontrada
     */
    @GetMapping("/collection/get/{collectionId}")
    public ResponseEntity<ProductCollectionDTO> getProductCollectionById(@PathVariable("collectionId") int collectionId){
        log.debug("Got prpduct/collection/" + collectionId);

        ProductCollectionDTO collection = collectionService.getById(collectionId);

        return new ResponseEntity<>(collection, 
                                    (collection != null) ?
                                    HttpStatus.OK : HttpStatus.NOT_FOUND);
    } 

    /**
     * Metodo para obtener todos los productos de una coleccion de productos.
     * @param collectionId ID de coleccion
     * @return Lista de productos
     */
    @GetMapping("/collection/{collectionId}/list")
    public ResponseEntity<List<ProductDTO>> getProductsOfProductCollection(@PathVariable("collectionId") int collectionId){
        log.debug("Got product/collection/{collectionId}/list" + collectionId);

        ProductCollectionDTO collection = collectionService.getById(collectionId);
        List<ProductDTO> products = productService.getByCollection(collection);

        if (collection != null && products != null) {
            return new ResponseEntity<>(products,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    } 
    
    /**
     * Metodo para obtener colecciones de productos por usuario.
     * @param userId ID de usuario
     * @return Lista de colecciones
     */
    @GetMapping("/collection/responsible/{userId}")
    public ResponseEntity<List<ProductCollectionDTO>> getProductCollectionsOfUser(@PathVariable("userId") int userId){
        log.debug("Got product/collection/all");

        UserDTO user = userService.getById(userId);
        List<ProductCollectionDTO> collections = collectionService.getByResponsible(user);

        if (user != null && collections != null) {
            return new ResponseEntity<>(collections,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para eliminar un producto por ID.
     * @param productId ID de producto
     * @return estatus de operacion
     */
    @AdminEndpoint
    @PostMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId){
        log.debug("Got /product/delete/" + productId);
        
        if (productService.getById(productId) != null) {
            productService.delete(productId);
            return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Product not found.", HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para eliminar una coleccion por ID.
     * @param productId ID de coleccion
     * @return estatus de operacion
     */
    @AdminEndpoint
    @PostMapping("/collection/delete/{collectionId}")
    public ResponseEntity<String> deleteProductCollection(@PathVariable("collectionId") int collectionId){
        log.debug("Got /product/collection/delete/" + collectionId);
        
        if (collectionService.getById(collectionId) != null) {
            collectionService.delete(collectionId);
            return new ResponseEntity<>("Product Collection deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Product Collection not found.", HttpStatus.NOT_FOUND);
    }

}
