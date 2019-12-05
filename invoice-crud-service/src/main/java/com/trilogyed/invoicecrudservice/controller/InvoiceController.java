package com.trilogyed.invoicecrudservice.controller;

import com.trilogyed.invoicecrudservice.dao.InvoiceRepository;
import com.trilogyed.invoicecrudservice.dto.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceRepository repo;

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return repo.findAll();
    }


    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        return repo.save(invoice);
    }

    @RequestMapping(value = "/invoice/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int id) {
        return repo.getOne(id);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Invoice updateInvoice( @RequestBody Invoice invoice) {
        return repo.save(invoice);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@RequestBody Invoice invoice) {
        repo.delete(invoice);
    }


}
