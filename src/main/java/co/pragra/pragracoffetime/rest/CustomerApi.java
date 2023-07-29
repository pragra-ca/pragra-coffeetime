package co.pragra.pragracoffetime.rest;


import co.pragra.pragracoffetime.entity.Customer;
import co.pragra.pragracoffetime.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
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

    @GetMapping("/api/customer/{id}")
    public Optional<Customer> getCustomer(@PathVariable int id ){
        return service.getById(id);
    }

    @PostMapping("/api/customer")
    public Customer create(@RequestBody  Customer customer) {
        return this.service.createCustomer(customer);
    }
}
