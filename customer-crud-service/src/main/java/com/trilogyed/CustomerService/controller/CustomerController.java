package com.trilogyed.CustomerService.controller;

import com.trilogyed.CustomerService.dao.CustomerRepository;
import com.trilogyed.CustomerService.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repository;


    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
    @GetMapping ("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById ( @PathVariable Integer id) {
        return repository.getOne(id);
    }

    @PostMapping("/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer ( @RequestBody Customer customer) {
        return repository.save(customer);
    }
    @PutMapping("/updateCustomer")
    public Customer updateCustomer ( @RequestBody Customer customer) {
        return repository.save(customer);
    }
    @DeleteMapping ("/deleteCustomer")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteCustomer( @RequestBody Customer customer) {
        repository.delete(customer);
    }


}
