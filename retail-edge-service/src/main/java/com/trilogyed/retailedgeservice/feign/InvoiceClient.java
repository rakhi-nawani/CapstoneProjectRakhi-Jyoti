package com.trilogyed.retailedgeservice.feign;


import com.trilogyed.retailedgeservice.domain.Invoice;
import com.trilogyed.retailedgeservice.domain.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices();


    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice);


    @RequestMapping(value = "/invoice/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int id);

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Invoice updateInvoice( @RequestBody Invoice invoice);

    @RequestMapping(value = "/invoice", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@RequestBody Invoice invoice);

    @RequestMapping(value = "/invoice/customerId/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findInvoiceByCustomerId(@PathVariable int customerId);
}
