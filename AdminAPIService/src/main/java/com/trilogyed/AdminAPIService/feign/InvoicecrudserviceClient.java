package com.trilogyed.AdminAPIService.feign;

import com.trilogyed.AdminAPIService.model.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "invoicecrudservice")
public interface InvoicecrudserviceClient {

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

}
