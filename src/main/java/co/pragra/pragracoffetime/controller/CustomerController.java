package co.pragra.pragracoffetime.controller;

import co.pragra.pragracoffetime.entity.Customer;
import co.pragra.pragracoffetime.repo.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    private CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer/create")
    public String getCreateCustomer(Model model){
        model.addAttribute("pageTitle", "Create A Customer | Pragra CoffeeTime");
        model.addAttribute("customer", new Customer());
        return "create-customer";
    }

    @PostMapping("/customer/create")
    public String create(@ModelAttribute Customer customer, Model model){
        repository.save(customer);
        model.addAttribute("customers", repository.findAll());
        return "customer";
    }
}
