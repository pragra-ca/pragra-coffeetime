package co.pragra.pragracoffetime.service;

import co.pragra.pragracoffetime.entity.Customer;
import co.pragra.pragracoffetime.exceptions.InvalidCustomerException;
import co.pragra.pragracoffetime.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public Customer createCustomer(Customer customer){
        if(customer.getFirstName()==null || customer.getFirstName().isEmpty()) {
            throw new InvalidCustomerException("Customer first name can't null or blank");
        }
       return this.repository.save(customer);
    }

    public Customer updateCustomer(Customer customer){
        if(customer.getId()==null || customer.getId()<=0) {
            throw new InvalidCustomerException("Invalid Customer ID");
        }
        Optional<Customer> optional = repository.findById(customer.getId());
        Customer cust = optional.orElseThrow(()->new InvalidCustomerException("Invalid Customer ID"));
        if(customer.getFirstName()==null || customer.getFirstName().isEmpty()) {
            throw new InvalidCustomerException("Customer first name can't null or blank");
        }
        return this.repository.save(customer);
    }

    public List<Customer> getAll(){
        return this.repository.findAll();
    }

    public Optional<Customer> getById(int id){
        return this.repository.findById(id);
    }

}
