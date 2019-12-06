package com.trilogyed.AdminAPIService.controller;

import com.trilogyed.AdminAPIService.model.Customer;
import com.trilogyed.AdminAPIService.service.AdminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminApiController {

    @Autowired
    AdminApiService service;


    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return null ;
    }
    @GetMapping ("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById ( @PathVariable Integer id){
        return null;
    }

    @PostMapping("/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer ( @RequestBody Customer customer){
        return null;
    }
    @PutMapping("/updateCustomer")
    public Customer updateCustomer ( @RequestBody Customer customer){
        return null;
    }
    @DeleteMapping ("/deleteCustomer")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteCustomer( @RequestBody Customer customer){

    }


}
