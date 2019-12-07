package com.trilogyed.AdminAPIService.controller;

import com.trilogyed.AdminAPIService.model.Customer;
import com.trilogyed.AdminAPIService.model.Invoice;
import com.trilogyed.AdminAPIService.model.Level;
import com.trilogyed.AdminAPIService.model.Product;
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
        return service.getAllCustomers();
    }
    @GetMapping ("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById ( @PathVariable Integer id){
        return service.getCustomerById(id);
    }

    @PostMapping("/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer ( @RequestBody Customer customer){
        return service.saveCustomer(customer);
    }
    @PutMapping("/updateCustomer")
    public Customer updateCustomer ( @RequestBody Customer customer){
        return service.updateCustomer(customer);
    }
    @DeleteMapping ("/deleteCustomer")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteCustomer( @RequestBody Customer customer){
        service.deleteCustomer(customer);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }
    @GetMapping ("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById ( @PathVariable Integer id) {
        return service.getProductById(id);
    }

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct ( @RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct ( @RequestBody Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping ("/deleteProduct")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteProduct( @RequestBody Product product) {
        service.deleteProduct(product);
    }

    @RequestMapping(value = "/levels", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Level> getAllLevels(){
        return service.getAllLevelUps();
    }


    @RequestMapping(value = "/levels", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Level addLevel(@RequestBody Level level){
        return service.saveLevel(level);
    }

    @RequestMapping(value = "/level/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Level getLevel(@PathVariable int id){
        return service.getLevelById(id);
    }

    @RequestMapping(value = "/level", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Level updateLevel( @RequestBody Level level){
        return service.updateLevel(level);
    }

    @RequestMapping(value = "/level", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevel(@RequestBody Level level){
        service.deleteLevel(level);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices(){ return service.getAllInvoice();}


    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice){return service.saveInvoice(invoice);}

    @RequestMapping(value = "/invoice/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int id){return service.geInvoiceById(id);}

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Invoice updateInvoice( @RequestBody Invoice invoice){return service.updateInvoice(invoice);}

    @RequestMapping(value = "/invoice", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@RequestBody Invoice invoice){
        service.deleteInvoice(invoice);
    }


}
