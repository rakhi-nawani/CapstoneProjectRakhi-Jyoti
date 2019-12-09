package com.trilogyed.retailedgeservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.retailedgeservice.domain.Customer;
import com.trilogyed.retailedgeservice.domain.Invoice;
import com.trilogyed.retailedgeservice.model.CustomerInvoice;
import com.trilogyed.retailedgeservice.service.ServiceLayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RetailControllerTest.class)
public class RetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer service;

    private CustomerInvoice customerInvoice;

    private ObjectMapper mapper = new ObjectMapper();

    private String jsonObject;

    @Test
    public void purchaseProduct() {
    }

    @Test
    public void submitInvoice() {
    }

    @Test
    public void getInvoiceById() {
    }

    @Test
    public void getAllInvoices() throws Exception {
        List<Invoice> invoiceList = Arrays.asList(
                new Invoice(1, 100, LocalDate.of(2019,11,30)),
                new Invoice(2, 100,LocalDate.of(2019,11,30))
        );
        jsonObject = mapper.writeValueAsString(invoiceList);
        when(service.getAllInvoice()).thenReturn(invoiceList);
        mockMvc.perform(MockMvcRequestBuilders.get("/invoices")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }

    @Test
    public void findInvoiceByCustomerId() {
    }

    @Test
    public void getProductById() {
    }

    @Test
    public void getProductByInvoiceId() {
    }

    @Test
    public void findLevelByCustomerId() {
    }
}