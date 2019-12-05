package com.trilogyed.invoicecrudservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.invoicecrudservice.dao.InvoiceRepository;
import com.trilogyed.invoicecrudservice.dto.Invoice;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    InvoiceRepository repo;

    private ObjectMapper mapper = new ObjectMapper();
    private String jsonObject;

    @Test
    public void getAllInvoices() throws Exception {
        List<Invoice> invoiceList = Arrays.asList(
                new Invoice(1, 100, LocalDate.of(2019,11,30)),
                new Invoice(2, 100,LocalDate.of(2019,11,30))
        );
        jsonObject = mapper.writeValueAsString(invoiceList);
        when(repo.findAll()).thenReturn(invoiceList);
        mockMvc.perform(MockMvcRequestBuilders.get("/invoices")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }

    @Test
    public void shouldGetInvoiceById() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        jsonObject = mapper.writeValueAsString(invoice);
        when(repo.getOne(1)).thenReturn(invoice);
        mockMvc.perform(MockMvcRequestBuilders.get("/invoice/id/1").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }


    @Test
    public void addInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        when(repo.save(invoice)).thenReturn(invoice);
        jsonObject = mapper.writeValueAsString(invoice);
        mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
                .contentType(APPLICATION_JSON)
                .content(jsonObject)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(jsonObject))
                .andExpect(status().isCreated());
    }


    @Test
    public void shouldUpdateInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        when(repo.save(invoice)).thenReturn(invoice);
        jsonObject = mapper.writeValueAsString(invoice);
        mockMvc.perform(MockMvcRequestBuilders.put("/invoice")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(content().json(jsonObject))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldDeleteInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        doNothing().when(repo).delete(invoice);
        jsonObject = mapper.writeValueAsString(invoice);
        mockMvc.perform(MockMvcRequestBuilders.delete("/invoice")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(repo, times(1)).delete(invoice);
    }


}