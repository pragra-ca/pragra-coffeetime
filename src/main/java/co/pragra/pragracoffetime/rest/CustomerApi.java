package co.pragra.pragracoffetime.rest;


import co.pragra.pragracoffetime.entity.Customer;
import co.pragra.pragracoffetime.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerService service;

    @GetMapping("/customer")
    public List<Customer> getAll(){
        return service.getAll();
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomer(@PathVariable int id ){
        return service.getById(id);
    }

    @PostMapping("/customer")
    public Customer create(@RequestBody  Customer customer) {
        return this.service.createCustomer(customer);
    }
}
