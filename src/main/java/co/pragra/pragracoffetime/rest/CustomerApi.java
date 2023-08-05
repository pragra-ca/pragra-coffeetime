package co.pragra.pragracoffetime.rest;


import co.pragra.pragracoffetime.dto.ErrorDto;
import co.pragra.pragracoffetime.entity.Customer;
import co.pragra.pragracoffetime.exceptions.CustomerNotFoundException;
import co.pragra.pragracoffetime.exceptions.InvalidCustomerException;
import co.pragra.pragracoffetime.service.CustomerService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Api(tags = "Customer")
public class CustomerApi {
    private final CustomerService service;

    @GetMapping("/api/customer")
    public List<Customer> getAll(
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestHeader(value = "X_SUPPLIER", required = false) String suppiler
    ){
        System.out.println(suppiler);
        return service.getAll(Optional.ofNullable(lastName));
    }

    @ApiParam(name = "ID", example = "123")
    @ApiResponses(value =  {
            @ApiResponse(code = 200, message = "Return the customer", response = Customer.class),
            @ApiResponse(code = 400, message = "Invalid ID data", response = ErrorDto.class),
            @ApiResponse(code = 404, message = "Customer Not Found", response = ErrorDto.class),
    })
    @GetMapping(value = "/api/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomer(@PathVariable int id ){
        if(id<0) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ErrorDto.builder().message("ID can't be negative")
                            .applicationId("APP1")
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .timestamp(Instant.now()).build()

            );
        }

        try {
            Customer customer = service.getById(id).orElseThrow(()->new CustomerNotFoundException("Can't find resource you are looking"));
            return ResponseEntity.status(200).body(customer);
        }catch (CustomerNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ErrorDto.builder().message(exception.getMessage())
                            .applicationId("APP1")
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .timestamp(Instant.now()).build()

            );
        }
    }

    @PostMapping("/api/customer")
    public Customer create(@RequestBody  Customer customer) {
        return this.service.createCustomer(customer);
    }
}
