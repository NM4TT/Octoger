package com.nmatute.octoger.accountingmanagement.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.SellDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.domain.service.ProductCollectionService;
import com.nmatute.octoger.accountingmanagement.domain.service.ProductOperationService;
import com.nmatute.octoger.accountingmanagement.domain.service.SellService;
import com.nmatute.octoger.accountingmanagement.domain.service.TransactionService;
import com.nmatute.octoger.accountingmanagement.domain.service.TypeService;
import com.nmatute.octoger.accountingmanagement.domain.service.UserService;
import com.nmatute.octoger.accountingmanagement.web.json.RegisterRequest;
import com.nmatute.octoger.accountingmanagement.web.json.SearchByDateRequest;
import com.nmatute.octoger.accountingmanagement.web.json.UpdateProductOperationRequest;
import com.nmatute.octoger.accountingmanagement.web.json.UpdateSellRequest;
import com.nmatute.octoger.accountingmanagement.web.json.UpdateTransactionRequest;
import com.nmatute.octoger.accountingmanagement.web.security.config.AdminEndpoint;

import lombok.RequiredArgsConstructor;

/**
 * Controlador de WebService
 */
@RestController
@RequestMapping("/accounting")
@RequiredArgsConstructor
public class Controller {
    
    private final Logger log = LoggerFactory.getLogger(Controller.class);
    private final ProductOperationService productOperationService;
    private final SellService sellService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final TypeService typeService;
    private final ProductCollectionService collectionService;
    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
    
    /**
     * Metodo para registrar una operacion contable.
     * @param request detalles de registro
     * @return estatus de operacion
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        log.debug("Got /accounting/register");
        
        if (!request.isEmpty(request.getDate()) &&
            !request.isEmpty(request.getType()) &&
            !request.isEmpty(String.valueOf(request.isSell())) &&
            !request.isEmpty(String.valueOf(request.getValue())) &&
            !request.isEmpty(String.valueOf(request.getProductAmount())) &&
            !request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getUserId()))
        ) {
            
            try {
                Date date = FORMATTER.parse(request.getDate());
                
                TypeDTO type = typeService.getByIdentifier(request.getType());
                ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
                UserDTO user = userService.getById(request.getUserId());
                
                //check de DTOs
                if(user == null || type == null || collection == null){
                    throw new Exception("Some DTOs were not found.");
                }

                //Se crea la transaccion
                TransactionDTO transaction = new TransactionDTO();
                transaction.setType(type);
                transaction.setValue(request.getValue());
                transaction.setDate(date);
                transaction = transactionService.save(transaction);

                //Se crea operacion de producto
                ProductOperationDTO operation = new ProductOperationDTO();
                operation.setTransaction(transaction);
                operation.setCollection(collection);
                operation.setDate(date);
                operation.setProductAmount(request.getProductAmount());
                operation.setType(type);
                operation.setUser(user);
                operation = productOperationService.save(operation);

                if (request.isSell()) {
                    SellDTO sell = new SellDTO();
                    sell.setCollection(collection);
                    sell.setDate(date);
                    sell.setProductOperation(operation);
                    sell.setUser(user);
                    sell = sellService.save(sell);
                }

                return new ResponseEntity<>("Info registered successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of info to register.");
            } catch (Exception ex){
                log.debug(ex.getMessage());
            }
        }
        
        return new ResponseEntity<>("Info not registered.", HttpStatus.BAD_REQUEST);   
    }

    /**
     * Metodo para actualizar operacion de producto.
     * @param request detalles de actualizacion
     * @return estatus de operacion
     */
    @PutMapping("/product-operation/update")
    public ResponseEntity<String> updateProductOperation(@RequestBody UpdateProductOperationRequest request){
        log.debug("Got /accounting/product-operation/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(request.getDate()) &&
            !request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getProductAmount())) &&
            !request.isEmpty(String.valueOf(request.getTransactionId())) && 
            !request.isEmpty(String.valueOf(request.getUserId())) && 
            !request.isEmpty(request.getType())
        ) {

            try {

                ProductOperationDTO operation = productOperationService.getById(request.getId());
                ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
                TransactionDTO transaction = transactionService.getById(request.getTransactionId());
                UserDTO user = userService.getById(request.getUserId());
                TypeDTO type = typeService.getByIdentifier(request.getType());

                if (operation == null || collection == null || transaction == null || user == null || type == null) {
                    throw new Exception("Some DTOs were not found.");
                }

                operation.setCollection(collection);
                operation.setTransaction(transaction);
                operation.setUser(user);
                operation.setType(type);
                operation.setProductAmount(request.getProductAmount());

                operation.setDate(FORMATTER.parse(request.getDate()));
                productOperationService.save(operation);
                log.debug("Product Operation updated successfully.");
                
                return new ResponseEntity<>("Product Operation updated successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of Product Operation.");
            } catch (Exception ex){
                log.debug(ex.getMessage());
            }
        }
        
        return new ResponseEntity<>("Product Operation not updated.", HttpStatus.BAD_REQUEST);   
    }

    /**
     * Metodo para actualizar venta.
     * @param request detalles de actualizacion
     * @return estatus de operacion
     */
    @PutMapping("/sell/update")
    public ResponseEntity<String> updateSell(@RequestBody UpdateSellRequest request){
        log.debug("Got /accounting/sell/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(request.getDate()) &&
            !request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getProductOperationId())) &&
            !request.isEmpty(String.valueOf(request.getUserId()))
        ) {

            try {

                SellDTO sell = sellService.getById(request.getId());
                ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
                ProductOperationDTO operation = productOperationService.getById(request.getProductOperationId());
                UserDTO user = userService.getById(request.getUserId());
                
                if (operation == null || collection == null || user == null || sell == null) {
                    throw new Exception("Some DTOs were not found.");
                }

                sell.setCollection(collection);
                sell.setProductOperation(operation);
                sell.setUser(user);

                sell.setDate(FORMATTER.parse(request.getDate()));
                sellService.save(sell);
                log.debug("Sell updated successfully.");
                
                return new ResponseEntity<>("Sell updated successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of sell.");
            } catch (Exception ex){
                log.debug(ex.getMessage());
            }
        }
        
        return new ResponseEntity<>("Sell not updated.", HttpStatus.BAD_REQUEST);        
    }

    /**
     * Metodo para actualizar transaccion.
     * @param request detalles de actualizacion
     * @return estatus de operacion
     */
    @PutMapping("/transaction/update")
    public ResponseEntity<String> updateTransaction(@RequestBody UpdateTransactionRequest request){
        log.debug("Got /accounting/transaction/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(request.getDate()) &&
            !request.isEmpty(request.getType()) &&
            !request.isEmpty(String.valueOf(request.getValue()))
        ) {
            
            try {

                TransactionDTO transaction = transactionService.getById(request.getId());
                TypeDTO type = typeService.getByIdentifier(request.getType());
                
                if (transaction == null || type == null) {
                    throw new Exception("Some DTOs were not found.");
                }
                
                transaction.setType(type);
                transaction.setValue(request.getValue());

                transaction.setDate(FORMATTER.parse(request.getDate()));
                transactionService.save(transaction);
                log.debug("Transaction updated successfully.");
                
                return new ResponseEntity<>("Transaction updated successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of transaction.");
            } catch (Exception ex){
                log.debug(ex.getMessage());
            }
        }
        
        return new ResponseEntity<>("Transaction not updated.", HttpStatus.BAD_REQUEST);        
    }
    
    /**
     * Metodo para obtener todas las operaciones de producto.
     * @return Lista de operaciones
     */
    @GetMapping("/product-operation/all")
    public ResponseEntity<List<ProductOperationDTO>> getAllProductOperations(){
        log.debug("Got /accounting/product-operation/all");

        List<ProductOperationDTO> products = productOperationService.getAll();

        return new ResponseEntity<>(products, 
                                   (products != null && products.size() > 0) ? 
                                   HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las ventas.
     * @return Lista de ventas
     */
    @GetMapping("/sell/all")
    public ResponseEntity<List<SellDTO>> getAllSells(){
        log.debug("Got /accounting/sell/all");

        List<SellDTO> sells = sellService.getAll();

        return new ResponseEntity<>(sells, 
                                   (sells != null && sells.size() > 0) ? 
                                   HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las transacciones.
     * @return Lista de transacciones
     */
    @GetMapping("/transaction/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        log.debug("Got /accounting/transaction/all");

        List<TransactionDTO> transactions = transactionService.getAll();

        return new ResponseEntity<>(transactions, 
                                   (transactions != null && transactions.size() > 0) ? 
                                   HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener una operacion de producto especifica.
     * @param operationId ID de operacion
     * @return operacion encontrada
     */
    @GetMapping("/product-operation/get/{operationId}")
    public ResponseEntity<ProductOperationDTO> getProductOperationById(@PathVariable("operationId") int operationId){
        log.debug("Got /accounting/product-operation/{operationId}");

        ProductOperationDTO operation = productOperationService.getById(operationId);

        return new ResponseEntity<>(operation, 
                                   (operation != null) ? 
                                   HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener una venta especifica.
     * @param sellId ID de venta
     * @return venta encontrada
     */
    @GetMapping("/sell/get/{sellId}")
    public ResponseEntity<SellDTO> getSellById(@PathVariable("sellId") int sellId){
        log.debug("Got /accounting/sell/{sellId}");

        SellDTO sell = sellService.getById(sellId);

        return new ResponseEntity<>(sell, 
                                   (sell != null) ? 
                                   HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener una transaccion especifica.
     * @param transactionId ID de transaccion
     * @return transaccion encontrada
     */
    @GetMapping("/transaction/get/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("transactionId") int transactionId){
        log.debug("Got /accounting/transaction/{transactionId}");

        TransactionDTO transaction = transactionService.getById(transactionId);

        return new ResponseEntity<>(transaction, 
                                   (transaction != null) ? 
                                   HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las operaciones de productos en un rango de fecha.
     * @param request detalles del rango de fecha
     * @return Lista de operaciones
     */
    @GetMapping("/product-operation/by-date")
    public ResponseEntity<List<ProductOperationDTO>> getProductOperationByDate(@RequestBody SearchByDateRequest request){
        log.debug("Got /accounting/product-operation/by-date");

        if (!request.isEmpty(request.getFromDate()) && !request.isEmpty(request.getToDate())) {
            
            try {
                Date from = FORMATTER.parse(request.getFromDate());
                Date to = FORMATTER.parse(request.getToDate());
                
                List<ProductOperationDTO> operations = productOperationService.getByDateRange(from, to);

                return new ResponseEntity<>(operations, 
                                            (operations != null && operations.size() > 0) ? 
                                            HttpStatus.OK : HttpStatus.NOT_FOUND);  

            } catch (ParseException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
            }


        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
    }

    /**
     * Metodo para obtener todas las ventas en un rango de fecha.
     * @param request detalles del rango de fecha
     * @return Lista de ventas
     */
    @GetMapping("/sell/by-date")
    public ResponseEntity<List<SellDTO>> getSellByDate(@RequestBody SearchByDateRequest request){
        log.debug("Got /accounting/sell/by-date");

        if (!request.isEmpty(request.getFromDate()) && !request.isEmpty(request.getToDate())) {
            
            try {
                Date from = FORMATTER.parse(request.getFromDate());
                Date to = FORMATTER.parse(request.getToDate());
                
                List<SellDTO> sells = sellService.getByDateRange(from, to);

                return new ResponseEntity<>(sells, 
                                            (sells != null && sells.size() > 0) ? 
                                            HttpStatus.OK : HttpStatus.NOT_FOUND); 

            } catch (ParseException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
            }


        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
    }

    /**
     * Metodo para obtener todas las transacciones en un rango de fecha.
     * @param request detalles del rango de fecha
     * @return Lista de transacciones
     */
    @GetMapping("/transaction/by-date")
    public ResponseEntity<List<TransactionDTO>> getTransactionByDate(@RequestBody SearchByDateRequest request){
        log.debug("Got /accounting/transaction/by-date");
        
        if (!request.isEmpty(request.getFromDate()) && !request.isEmpty(request.getToDate())) {
            
            try {
                Date from = FORMATTER.parse(request.getFromDate());
                Date to = FORMATTER.parse(request.getToDate());

                List<TransactionDTO> transactions = transactionService.getByDateRange(from, to);

                return new ResponseEntity<>(transactions, 
                                            (transactions != null && transactions.size() > 0) ? 
                                            HttpStatus.OK : HttpStatus.NOT_FOUND); 

            } catch (ParseException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
            }


        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
    }

    /**
     * Metodo para obtener todas las operaciones de producto de una coleccion de productos.
     * @param collectionId ID de coleccion
     * @return Lista de operaciones
     */
    @GetMapping("/product-operation/collection/{collectionId}")
    public ResponseEntity<List<ProductOperationDTO>> getProductOperationByCollection(@PathVariable("collectionId") int collectionId){
        log.debug("Got /accounting/product-operation/{collectionId}");

        ProductCollectionDTO collection = collectionService.getById(collectionId);
        List<ProductOperationDTO> operations = productOperationService.getByCollection(collection);
        
        if (collection != null && operations != null) {
            return new ResponseEntity<>(operations, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las ventas de una coleccion de productos.
     * @param collectionId ID de coleccion
     * @return Lista de ventas
     */
    @GetMapping("/sell/collection/{collectionId}")
    public ResponseEntity<List<SellDTO>> getSellByCollection(@PathVariable("collectionId") int collectionId){
        log.debug("Got /accounting/sell/{collectionId}");

        ProductCollectionDTO collection = collectionService.getById(collectionId);
        List<SellDTO> sells = sellService.getByCollection(collection);

        if (collection != null && sells != null) {
            return new ResponseEntity<>(sells, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las operaciones de productos de un usuario.
     * @param userId ID de usuario
     * @return Lista de operaciones
     */
    @GetMapping("/product-operation/user/{userId}")
    public ResponseEntity<List<ProductOperationDTO>> getProductOperationByResponsible(@PathVariable("userId") int userId){
        log.debug("Got /accounting/product-operation/{userId}");

        UserDTO user = userService.getById(userId);
        List<ProductOperationDTO> operations = productOperationService.getByResponsible(user);

        if (user != null && operations != null) {
            return new ResponseEntity<>(operations, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las ventas de un usuario.
     * @param userId ID de usuario
     * @return Lista de ventas
     */
    @GetMapping("/sell/user/{userId}")
    public ResponseEntity<List<SellDTO>> getSellByResponsible(@PathVariable("userId") int userId){
        log.debug("Got /accounting/sell/{userId}");

        UserDTO user = userService.getById(userId);
        List<SellDTO> sells = sellService.getByResponsible(user);

        if (user != null && sells != null) {
            return new ResponseEntity<>(sells, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las operaciones de producto de un tipo.
     * @param type identificador del tipo
     * @return Lista de operaciones
     */
    @GetMapping("/product-operation/type/{type}")
    public ResponseEntity<List<ProductOperationDTO>> getProductOperationByType(@PathVariable("type") String type){
        log.debug("Got /accounting/product-operation/{type}");

        TypeDTO t = typeService.getByIdentifier(type);
        List<ProductOperationDTO> operations = productOperationService.getByType(t);

        if (t != null && operations != null) {
            return new ResponseEntity<>(operations, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener todas las transacciones de un tipo.
     * @param type identificador del tipo
     * @return Lista de transacciones
     */
    @GetMapping("/transaction/type/{type}")
    public ResponseEntity<List<TransactionDTO>> getSellByType(@PathVariable("type") String type){
        log.debug("Got /accounting/transaction/{type}");

        TypeDTO t = typeService.getByIdentifier(type);
        List<TransactionDTO> transactions = transactionService.getByType(t);

        if (t != null && transactions != null) {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener una operacion de producto por ID de transaccion.
     * @param transactionId ID de transaccion
     * @return operacion encontrada
     */
    @GetMapping("/product-operation/transaction/{transactionId}")
    public ResponseEntity<ProductOperationDTO> getProductOperationByTransactionId(@PathVariable("transactionId") int transactionId){
        log.debug("Got /accounting/product-operation/{transactionId}");

        TransactionDTO t = transactionService.getById(transactionId);
        ProductOperationDTO operation = productOperationService.getByTransaction(t);

        if (t != null && operation != null) {
            return new ResponseEntity<>(operation, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para obtener una venta por ID de operacion de producto.
     * @param operationId ID de operacion
     * @return venta encontrada
     */
    @GetMapping("/sell/operation/{operationId}")
    public ResponseEntity<SellDTO> getSellByOperationId(@PathVariable("operationId") int operationId){
        log.debug("Got /accounting/sell/{operationId}");

        ProductOperationDTO po = productOperationService.getById(operationId);
        SellDTO sell = sellService.getByProductOperation(po);

        if (po != null && sell != null) {
            return new ResponseEntity<>(sell, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Metodo para eliminar una operacion de producto por ID.
     * @param operationId ID de operacion
     * @return estatus de operacion realizada
     */
    @AdminEndpoint
    @PostMapping("/product-operation/delete/{operationId}")
    public ResponseEntity<String> deleteProductOperation(@PathVariable("operationId") int operationId){
        log.debug("Got /accounting/product-operation/delete/{operationId}");
        
        if (productOperationService.getById(operationId) != null) {
            productOperationService.delete(operationId);
            return new ResponseEntity<>("Product operation deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Product operation not found.", HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para eliminar una venta por ID.
     * @param sellId ID de venta
     * @return estatus de operacion realizada
     */
    @AdminEndpoint
    @PostMapping("/sell/delete/{sellId}")
    public ResponseEntity<String> deleteSell(@PathVariable("sellId") int sellId){
        log.debug("Got /accounting/sell/delete/{sellId}");

        if (sellService.getById(sellId) != null) {
            sellService.delete(sellId);
            return new ResponseEntity<>("Sell deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Sell not found.", HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para eliminar una transaccion por ID.
     * @param transactionId ID de transaccion
     * @return estatus de operacion realizada
     */
    @AdminEndpoint
    @PostMapping("/transaction/delete/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("transactionId") int transactionId){
        log.debug("Got /accounting/transaction/delete/{transactionId}");

        if (transactionService.getById(transactionId) != null) {
            transactionService.delete(transactionId);
            return new ResponseEntity<>("Transaction deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Transaction not found.", HttpStatus.NOT_FOUND);
    }

}
