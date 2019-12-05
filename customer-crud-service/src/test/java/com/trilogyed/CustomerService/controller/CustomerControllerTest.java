package com.trilogyed.CustomerService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.CustomerService.dao.CustomerRepository;
import com.trilogyed.CustomerService.dto.Customer;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerRepository repository;

    private ObjectMapper mapper = new ObjectMapper();
    private String jsonObject;

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void shouldGetAllCustomers() throws Exception {

        List<Customer> customerList = Arrays.asList(
                new Customer("John", "Ray", "75 Kristin cr", "Schaumburg", "60195", "john@gmail.com", "2245781671 " ),
                new Customer("Kiti", "Ray", "75 Kristin cr", "Schaumburg", "60195", "kiti@gmail.com", "2255781671 " )
        );
        jsonObject = mapper.writeValueAsString(customerList);
        when(repository.findAll()).thenReturn(customerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
               // .andExpect(jsonPath("$[0].isbn", Matchers.is("ABCD")))
                //.andExpect(jsonPath("$[1].title", Matchers.is("one neight at call center")))
                .andExpect(content().json(jsonObject));
    }


    @Test
    public void shouldGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671 ");
        jsonObject = mapper.writeValueAsString(customer);
        when(repository.getOne(1)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.is("ADCD")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Two States")))
                .andExpect(content().json(jsonObject));
    }
    @Test
    public void addCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671 ");
        when(repository.save(customer)).thenReturn(customer);
        jsonObject = mapper.writeValueAsString(customer);
        mockMvc.perform(MockMvcRequestBuilders.post("/addCustomer")
                .contentType(APPLICATION_JSON)
                .content(jsonObject)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(jsonObject))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.is("ADCD")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Two States")))
                .andExpect(status().isCreated());
    }
    @Test
    public void shouldUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671 ");
        when(repository.save(customer)).thenReturn(customer);
        jsonObject = mapper.writeValueAsString(customer);
        mockMvc.perform(MockMvcRequestBuilders.put("/updateCustomer")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(content().json(jsonObject))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("one neight at call center")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.is("EFGH")))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldDeleteCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671 ");
        jsonObject = mapper.writeValueAsString(customer);
        doNothing().when(repository).delete(customer);
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCustomer")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(repository, times(1)).delete(customer);
    }


}