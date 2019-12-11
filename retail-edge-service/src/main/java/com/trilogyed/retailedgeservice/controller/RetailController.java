package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.domain.*;
import com.trilogyed.retailedgeservice.feign.CustomerServiceFeignClient;
import com.trilogyed.retailedgeservice.feign.InvoiceClient;
import com.trilogyed.retailedgeservice.feign.LevelUpClient;
import com.trilogyed.retailedgeservice.feign.ProductServiceFeignClient;
import com.trilogyed.retailedgeservice.model.CustomerInvoice;
import com.trilogyed.retailedgeservice.service.ServiceLayer;
import com.trilogyed.retailedgeservice.util.messages.LevelUpPointsEntry;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RetailController {

    public static final String EXCHANGE = "queue-demo-exchange";
    public static final String ROUTING_KEY = "levelup.points.add.account.controller";



    private RabbitTemplate rabbitTemplate;

//    public RetailController() {
//    }

    @Autowired
    public RetailController(RabbitTemplate rabbitTemplate, ServiceLayer service, LevelUpClient levelUpClient, InvoiceClient invoiceClient, ProductServiceFeignClient productServiceFeignClient, CustomerServiceFeignClient customerServiceFeignClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.service = service;
        this.levelUpClient = levelUpClient;
        this.invoiceClient = invoiceClient;
        this.productServiceFeignClient = productServiceFeignClient;
        this.customerServiceFeignClient = customerServiceFeignClient;
    }



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


    @RequestMapping(value = "/level", method = RequestMethod.POST)
    public String addLlevel(@RequestBody Level level) {
        // create message to send to email list creation queue
        LevelUpPointsEntry msg = new LevelUpPointsEntry(level.getLevelUpId(), level.getCustomerId(), level.getPoints());
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
        System.out.println("Points Sumbitted");
        return "LevelUp Points added";
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerInvoice purchaseProduct(@RequestBody Order order) {
        return service.purchaceProduct(order);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice submitInvoice(@RequestBody Invoice invoice) {
        return service.submitInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CustomerInvoice getInvoiceById(@PathVariable int id) {

        if (id < 1) {
            throw new IllegalArgumentException("MotoId must be greater than 0.");
        }
        return service.getInvoicebyId(id);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceClient.getAllInvoices();
    }

    @RequestMapping(value = "/invoice/customerId/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomerId(@PathVariable int customerId) {
        return invoiceClient.findInvoiceByCustomerId(customerId);
    }

    @RequestMapping(value = "/products/inventory", method = RequestMethod.GET)
    public List<Product> getProductsInInventory() {

        return service.searchInventory();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable Integer id) {
        if (id < 1) {
            throw new IllegalArgumentException("MotoId must be greater than 0.");
        }
        return service.getProductByProductId(id);

    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getcustomerById(@PathVariable Integer customerId) {
        if (customerId < 1) {
            throw new IllegalArgumentException("customerId must be greater than 0.");
        }
        return service.findCustomer(customerId);

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



