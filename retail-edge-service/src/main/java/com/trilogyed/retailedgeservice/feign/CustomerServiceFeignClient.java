package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "CustomerService")
public interface CustomerServiceFeignClient {

    @GetMapping("/customers")
    public List<Customer> getAllCustomers();
    @GetMapping ("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable Integer id);

    @PostMapping("/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer);
    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer customer);
    @DeleteMapping ("/deleteCustomer")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestBody Customer customer);


}
