package com.nmatute.octoger.accountingmanagement.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.nmatute.octoger.accountingmanagement.web.json.CreateProductOperationRequest;
import com.nmatute.octoger.accountingmanagement.web.json.CreateSellRequest;
import com.nmatute.octoger.accountingmanagement.web.json.CreateTransactionRequest;
import com.nmatute.octoger.accountingmanagement.web.json.SearchByDateRequest;
import com.nmatute.octoger.accountingmanagement.web.json.UpdateProductOperationRequest;
import com.nmatute.octoger.accountingmanagement.web.json.UpdateSellRequest;
import com.nmatute.octoger.accountingmanagement.web.json.UpdateTransactionRequest;
import com.nmatute.octoger.accountingmanagement.web.security.config.AdminEndpoint;

import lombok.RequiredArgsConstructor;

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
    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH); //7-Jun-2021
    
    @PostMapping("/product-operation/create")
    public ResponseEntity<String> createProductOperation(@RequestBody CreateProductOperationRequest request){
        log.debug("Got /accounting/product-operation/create");
        
        if (!request.isEmpty(request.getDate()) &&
            !request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getProductAmount())) &&
            !request.isEmpty(String.valueOf(request.getTransactionId())) && 
            !request.isEmpty(String.valueOf(request.getUserId())) && 
            !request.isEmpty(request.getType())
        ) {
            
            ProductOperationDTO operation = new ProductOperationDTO();
            ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
            TransactionDTO transaction = transactionService.getById(request.getTransactionId());
            UserDTO user = userService.getById(request.getUserId());
            TypeDTO type = typeService.getByIdentifier(request.getType());
            operation.setCollection(collection);
            operation.setTransaction(transaction);
            operation.setUser(user);
            operation.setType(type);
            operation.setProductAmount(request.getProductAmount());

            try {
                operation.setDate(FORMATTER.parse(request.getDate()));
                productOperationService.save(operation);
                log.debug("Product Operation created successfully.");
                
                return new ResponseEntity<>("Product Operation created successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of Product Operation.");
            } 
        }
        
        return new ResponseEntity<>("Product Operation not created." + request.toString(), HttpStatus.BAD_REQUEST);   
    }

    @PostMapping("/sell/create")
    public ResponseEntity<String> createSell(@RequestBody CreateSellRequest request){
        log.debug("Got /accounting/sell/create");

        if (!request.isEmpty(request.getDate()) &&
            !request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getProductOperationId())) &&
            !request.isEmpty(String.valueOf(request.getUserId()))
        ) {
            
            SellDTO sell = new SellDTO();
            ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
            ProductOperationDTO operation = productOperationService.getById(request.getProductOperationId());
            UserDTO user = userService.getById(request.getUserId());
            sell.setCollection(collection);
            sell.setProductOperation(operation);
            sell.setUser(user);

            try {
                sell.setDate(FORMATTER.parse(request.getDate()));
                sellService.save(sell);
                log.debug("Sell created successfully.");
                
                return new ResponseEntity<>("Sell created successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of sell.");
            }
        }
        
        return new ResponseEntity<>("Sell not created.", HttpStatus.BAD_REQUEST);  
    }

    @PostMapping("/transaction/create")
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionRequest request){
        log.debug("Got /accounting/transaction/create");
        
        if (!request.isEmpty(request.getDate()) &&
            !request.isEmpty(request.getType()) &&
            !request.isEmpty(String.valueOf(request.getValue()))
        ) {
            
            TransactionDTO transaction = new TransactionDTO();
            TypeDTO type = typeService.getByIdentifier(request.getType());
            transaction.setType(type);
            transaction.setValue(request.getValue());
            try {
                transaction.setDate(FORMATTER.parse(request.getDate()));
                transactionService.save(transaction);
                log.debug("Transaction updated successfully.");
                
                return new ResponseEntity<>("Transaction created successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of transaction.");
            }
        }
        
        return new ResponseEntity<>("Transaction not created.", HttpStatus.BAD_REQUEST);    
    }

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
            
            ProductOperationDTO operation = productOperationService.getById(request.getId());
            ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
            TransactionDTO transaction = transactionService.getById(request.getTransactionId());
            UserDTO user = userService.getById(request.getUserId());
            TypeDTO type = typeService.getByIdentifier(request.getType());
            operation.setCollection(collection);
            operation.setTransaction(transaction);
            operation.setUser(user);
            operation.setType(type);
            operation.setProductAmount(request.getProductAmount());

            try {
                operation.setDate(FORMATTER.parse(request.getDate()));
                productOperationService.save(operation);
                log.debug("Product Operation updated successfully.");
                
                return new ResponseEntity<>("Product Operation updated successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of Product Operation.");
            }
        }
        
        return new ResponseEntity<>("Product Operation not updated.", HttpStatus.BAD_REQUEST);   
    }

    @PutMapping("/sell/update")
    public ResponseEntity<String> updateSell(@RequestBody UpdateSellRequest request){
        log.debug("Got /accounting/sell/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(request.getDate()) &&
            !request.isEmpty(String.valueOf(request.getCollectionId())) &&
            !request.isEmpty(String.valueOf(request.getProductOperationId())) &&
            !request.isEmpty(String.valueOf(request.getUserId()))
        ) {
            
            SellDTO sell = sellService.getById(request.getId());
            ProductCollectionDTO collection = collectionService.getById(request.getCollectionId());
            ProductOperationDTO operation = productOperationService.getById(request.getProductOperationId());
            UserDTO user = userService.getById(request.getUserId());
            sell.setCollection(collection);
            sell.setProductOperation(operation);
            sell.setUser(user);

            try {
                sell.setDate(FORMATTER.parse(request.getDate()));
                sellService.save(sell);
                log.debug("Sell updated successfully.");
                
                return new ResponseEntity<>("Sell updated successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of sell.");
            }
        }
        
        return new ResponseEntity<>("Sell not updated.", HttpStatus.BAD_REQUEST);        
    }

    @PutMapping("/transaction/update")
    public ResponseEntity<String> updateTransaction(@RequestBody UpdateTransactionRequest request){
        log.debug("Got /accounting/transaction/update");
        
        if (!request.isEmpty(String.valueOf(request.getId())) &&
            !request.isEmpty(request.getDate()) &&
            !request.isEmpty(request.getType()) &&
            !request.isEmpty(String.valueOf(request.getValue()))
        ) {
            
            TransactionDTO transaction = transactionService.getById(request.getId());
            TypeDTO type = typeService.getByIdentifier(request.getType());
            transaction.setType(type);
            transaction.setValue(request.getValue());
            try {
                transaction.setDate(FORMATTER.parse(request.getDate()));
                transactionService.save(transaction);
                log.debug("Transaction updated successfully.");
                
                return new ResponseEntity<>("Transaction updated successfully.", HttpStatus.OK);
            } catch (ParseException e) {
                log.debug("Error parsing date of transaction.");
            }
        }
        
        return new ResponseEntity<>("Transaction not updated.", HttpStatus.BAD_REQUEST);        
    }
    
    @GetMapping("/product-operation/all")
    public ResponseEntity<List<ProductOperationDTO>> getAllProductOperations(){
        log.debug("Got /accounting/product-operation/all");
        return new ResponseEntity<>(productOperationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/sell/all")
    public ResponseEntity<List<SellDTO>> getAllSells(){
        log.debug("Got /accounting/sell/all");
        return new ResponseEntity<>(sellService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/transaction/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        log.debug("Got /accounting/transaction/all");
        return new ResponseEntity<>(transactionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/product-operation/get/{operationId}")
    public ResponseEntity<ProductOperationDTO> getProductOperationById(@PathVariable("operationId") int operationId){
        log.debug("Got /accounting/product-operation/{operationId}");
        return new ResponseEntity<>(productOperationService.getById(operationId), HttpStatus.OK);
    }

    @GetMapping("/sell/get/{sellId}")
    public ResponseEntity<SellDTO> getSellById(@PathVariable("sellId") int sellId){
        log.debug("Got /accounting/sell/{sellId}");
        return new ResponseEntity<>(sellService.getById(sellId), HttpStatus.OK);
    }

    @GetMapping("/transaction/get/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("transactionId") int transactionId){
        log.debug("Got /accounting/transaction/{transactionId}");
        return new ResponseEntity<>(transactionService.getById(transactionId), HttpStatus.OK);
    }

    @GetMapping("/product-operation/by-date")
    public ResponseEntity<List<ProductOperationDTO>> getProductOperationByDate(@RequestBody SearchByDateRequest request){
        log.debug("Got /accounting/product-operation/by-date");

        if (!request.isEmpty(request.getFromDate()) && !request.isEmpty(request.getToDate())) {
            
            try {
                Date from = FORMATTER.parse(request.getFromDate());
                Date to = FORMATTER.parse(request.getToDate());

                return new ResponseEntity<>(productOperationService.getByDateRange(from, to), HttpStatus.OK); 

            } catch (ParseException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
            }


        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
    }

    @GetMapping("/sell/by-date")
    public ResponseEntity<List<SellDTO>> getSellByDate(@RequestBody SearchByDateRequest request){
        log.debug("Got /accounting/sell/by-date");

        if (!request.isEmpty(request.getFromDate()) && !request.isEmpty(request.getToDate())) {
            
            try {
                Date from = FORMATTER.parse(request.getFromDate());
                Date to = FORMATTER.parse(request.getToDate());

                return new ResponseEntity<>(sellService.getByDateRange(from, to), HttpStatus.OK); 

            } catch (ParseException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
            }


        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
    }

    @GetMapping("/transaction/by-date")
    public ResponseEntity<List<TransactionDTO>> getTransactionByDate(@RequestBody SearchByDateRequest request){
        log.debug("Got /accounting/transaction/by-date");
        
        if (!request.isEmpty(request.getFromDate()) && !request.isEmpty(request.getToDate())) {
            
            try {
                Date from = FORMATTER.parse(request.getFromDate());
                Date to = FORMATTER.parse(request.getToDate());

                return new ResponseEntity<>(transactionService.getByDateRange(from, to), HttpStatus.OK); 

            } catch (ParseException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
            }


        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
    }

    @GetMapping("/product-operation/collection/{collectionId}")
    public ResponseEntity<List<ProductOperationDTO>> getProductOperationByCollection(@PathVariable("collectionId") int collectionId){
        log.debug("Got /accounting/product-operation/{collectionId}");

        Optional<ProductCollectionDTO> collection = Optional.of(collectionService.getById(collectionId));

        if (collection.isPresent()) {
            return new ResponseEntity<>(productOperationService.getByCollection(collection.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sell/collection/{collectionId}")
    public ResponseEntity<List<SellDTO>> getSellByCollection(@PathVariable("collectionId") int collectionId){
        log.debug("Got /accounting/sell/{collectionId}");

        Optional<ProductCollectionDTO> collection = Optional.of(collectionService.getById(collectionId));

        if (collection.isPresent()) {
            return new ResponseEntity<>(sellService.getByCollection(collection.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product-operation/user/{userId}")
    public ResponseEntity<List<ProductOperationDTO>> getProductOperationByResponsible(@PathVariable("userId") int userId){
        log.debug("Got /accounting/product-operation/{userId}");

        Optional<UserDTO> user = Optional.of(userService.getById(userId));

        if (user.isPresent()) {
            return new ResponseEntity<>(productOperationService.getByResponsible(user.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sell/user/{userId}")
    public ResponseEntity<List<SellDTO>> getSellByResponsible(@PathVariable("userId") int userId){
        log.debug("Got /accounting/sell/{userId}");

        Optional<UserDTO> user = Optional.of(userService.getById(userId));

        if (user.isPresent()) {
            return new ResponseEntity<>(sellService.getByResponsible(user.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product-operation/type/{type}")
    public ResponseEntity<List<ProductOperationDTO>> getProductOperationByType(@PathVariable("type") String type){
        log.debug("Got /accounting/product-operation/{type}");

        Optional<TypeDTO> t = Optional.of(typeService.getByIdentifier(type));

        if (t.isPresent()) {
            return new ResponseEntity<>(productOperationService.getByType(t.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/transaction/type/{type}")
    public ResponseEntity<List<TransactionDTO>> getSellByType(@PathVariable("type") String type){
        log.debug("Got /accounting/transaction/{type}");

        Optional<TypeDTO> t = Optional.of(typeService.getByIdentifier(type));

        if (t.isPresent()) {
            return new ResponseEntity<>(transactionService.getByType(t.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product-operation/transaction/{transactionId}")
    public ResponseEntity<ProductOperationDTO> getProductOperationByTransactionId(@PathVariable("transactionId") int transactionId){
        log.debug("Got /accounting/product-operation/{transactionId}");

        Optional<TransactionDTO> t = Optional.of(transactionService.getById(transactionId));

        if (t.isPresent()) {
            return new ResponseEntity<>(productOperationService.getByTransaction(t.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sell/operation/{operationId}")
    public ResponseEntity<SellDTO> getSellByOperationId(@PathVariable("operationId") int operationId){
        log.debug("Got /accounting/sell/{operationId}");

        Optional<ProductOperationDTO> po = Optional.of(productOperationService.getById(operationId));

        if (po.isPresent()) {
            return new ResponseEntity<>(sellService.getByProductOperation(po.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    @AdminEndpoint
    @PostMapping("/product-operation/delete/{operationId}")
    public ResponseEntity<String> deleteProductOperation(@PathVariable("operationId") int operationId){
        log.debug("Got /accounting/product-operation/delete/{operationId}");
        
        Optional<ProductOperationDTO> optionalOperation = Optional.of(productOperationService.getById(operationId));

        if (optionalOperation.isPresent()) {
            productOperationService.delete(operationId);
            return new ResponseEntity<>("Product operation deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Product operation not found.", HttpStatus.NOT_FOUND);
    }

    @AdminEndpoint
    @PostMapping("/sell/delete/{sellId}")
    public ResponseEntity<String> deleteSell(@PathVariable("sellId") int sellId){
        log.debug("Got /accounting/sell/delete/{sellId}");

        Optional<SellDTO> optionalSell = Optional.of(sellService.getById(sellId));

        if (optionalSell.isPresent()) {
            sellService.delete(sellId);
            return new ResponseEntity<>("Sell deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Sell not found.", HttpStatus.NOT_FOUND);
    }

    @AdminEndpoint
    @PostMapping("/transaction/delete/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("transactionId") int transactionId){
        log.debug("Got /accounting/transaction/delete/{transactionId}");

        Optional<TransactionDTO> optionalTransaction = Optional.of(transactionService.getById(transactionId));

        if (optionalTransaction.isPresent()) {
            transactionService.delete(transactionId);
            return new ResponseEntity<>("Transaction deleted successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Transaction not found.", HttpStatus.NOT_FOUND);
    }

}
