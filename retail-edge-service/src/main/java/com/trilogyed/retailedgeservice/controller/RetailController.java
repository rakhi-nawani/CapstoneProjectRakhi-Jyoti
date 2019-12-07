package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.domain.Invoice;
import com.trilogyed.retailedgeservice.domain.Item;
import com.trilogyed.retailedgeservice.domain.Level;
import com.trilogyed.retailedgeservice.domain.Product;
import com.trilogyed.retailedgeservice.feign.CustomerServiceFeignClient;
import com.trilogyed.retailedgeservice.feign.InvoiceClient;
import com.trilogyed.retailedgeservice.feign.LevelUpClient;
import com.trilogyed.retailedgeservice.feign.ProductServiceFeignClient;
import com.trilogyed.retailedgeservice.model.CustomerInvoice;
import com.trilogyed.retailedgeservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RetailController {

    @Autowired
    private final ServiceLayer service;

    @Autowired
    private final LevelUpClient levelUpClient;

    @Autowired
    private final InvoiceClient invoiceClient;

    private final ProductServiceFeignClient productServiceFeignClient;

    private final CustomerServiceFeignClient customerServiceFeignClient;

    public RetailController(LevelUpClient client, ServiceLayer service, LevelUpClient levelUpClient, InvoiceClient invoiceClient, ProductServiceFeignClient productServiceFeignClient, CustomerServiceFeignClient customerServiceFeignClient) {
        this.service = service;
        this.levelUpClient = levelUpClient;
        this.invoiceClient = invoiceClient;

        this.productServiceFeignClient = productServiceFeignClient;
        this.customerServiceFeignClient = customerServiceFeignClient;
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerInvoice purchaseProduct(@RequestBody int quantity, int customerId, int productId) {
        return service.purchaceProduct(quantity, customerId, productId);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerInvoice submitInvoice(@RequestBody Invoice invoice) {
        return service.submitInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CustomerInvoice getInvoiceById(@PathVariable int id) {
        return service.getInvoicebyId(id);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceClient.getAllInvoices();
    }

//    @RequestMapping(value = "/invoice/customerId/{customerId}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<Invoice> getInvoicesByCustomerId(@PathVariable int customerId) {
//        return invoiceClient.findInvoiceByCustomerId(customerId);
//    }

    @RequestMapping(value = "/invoice/customerId/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerInvoice> findInvoiceByCustomerId(@PathVariable int customerId) {
        return service.getInvoicebyCustomerIdId(customerId);
    }
//    //Jyotis work
//    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
//    public List<Product> getProductsInInventory() {
//        return null;
//    }

    @GetMapping ("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById (@PathVariable Integer id) {
        return productServiceFeignClient.getProductById(id);

    }

    @RequestMapping(value = "/products/invoiceId/{id}", method = RequestMethod.GET)
    public List<Item> getProductByInvoiceId(@PathVariable int invoiceId) {
        return service.getProductsbyInvoiceId(invoiceId);
    }

    @RequestMapping(value = "/level/customerId/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public int findLevelByCustomerId(@PathVariable int customerId) {
        return service.getLevelupPointsbyCustomerId(customerId);
    }



//    @RequestMapping(value = "/levels", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<Level> getAllLevels() {
//        return levelUpClient.getAllLevels();
//    }
//
//
//    @RequestMapping(value = "/levels", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public Level addLevel(@RequestBody Level level) {
//        return levelUpClient.addLevel(level);
//    }
//
//    @RequestMapping(value = "/level/id/{id}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public Level getLevel(@PathVariable int id) {
//        return levelUpClient.getLevel(id);
//    }
//
//    @RequestMapping(value = "/level", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public Level updateLevel( @RequestBody Level level) {
//        return levelUpClient.updateLevel(level);
//    }
//
//    @RequestMapping(value = "/level", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteLevel(@RequestBody Level level) {
//        levelUpClient.deleteLevel(level);
//    }
//
//    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public CustomerInvoice updateInvoice( @RequestBody Invoice invoice) {
//        return service.updateInvoice(invoice);
//    }
//
//    @RequestMapping(value = "/invoice", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteInvoice(@RequestBody Invoice invoice) {
//        service.deleteInvoice(invoice);
//    }

        // Admin getInvoicesByCustomerId








}

