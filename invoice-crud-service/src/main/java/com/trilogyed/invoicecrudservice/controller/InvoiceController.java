package com.trilogyed.invoicecrudservice.controller;

import com.trilogyed.invoicecrudservice.dao.InvoiceRepository;
import com.trilogyed.invoicecrudservice.dao.ItemRepository;
import com.trilogyed.invoicecrudservice.dto.Invoice;
import com.trilogyed.invoicecrudservice.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceRepository repo;

    @Autowired
    ItemRepository itemRepo;

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return repo.findAll();
    }


    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice) {
//        List<Item> tempItemList = invoice.getItems();
//        invoice.setItems(tempItemList);
//        repo.save(invoice);
//        tempItemList.stream().forEach(item ->{
//            item.setInvoiceId(invoice.getInvoiceId());
//            itemRepo.save(item);
//        });
//        invoice.setItems(tempItemList);
        Invoice outInvoice = new Invoice();
        outInvoice.setCustomerId(invoice.getCustomerId());
        outInvoice.setPurchaseDate(invoice.getPurchaseDate());
        outInvoice.setItems(invoice.getItems());
        return repo.save(outInvoice);
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


    // Custom Method
    @RequestMapping(value = "/invoice/customerId/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findInvoiceByCustomerId(@PathVariable int customerId) {
        return repo.findInvoiceByCustomerId(customerId);
    }

}
