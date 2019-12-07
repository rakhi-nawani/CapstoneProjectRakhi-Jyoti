package com.trilogyed.retailedgeservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.retailedgeservice.domain.Customer;
import com.trilogyed.retailedgeservice.model.CustomerInvoice;
import com.trilogyed.retailedgeservice.service.ServiceLayer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

}