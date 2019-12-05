package com.trilogyed.ProductService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.ProductService.dao.ProductRepository;
import com.trilogyed.ProductService.dto.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductRepository repository;

    private ObjectMapper mapper = new ObjectMapper();
    private String jsonObject;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void shouldGetAllProducts() throws Exception {

        List<Product> productList = Arrays.asList(
                new Product("TV", "this ia an OLD TV", 2000.99, 1500.00 , 5 ),
                new Product("Instant Pot", "Electrical Presuure Cooker", 150.55, 125.00, 4 )
        );
        jsonObject = mapper.writeValueAsString(productList);
        when(repository.findAll()).thenReturn(productList);
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                // .andExpect(jsonPath("$[0].isbn", Matchers.is("ABCD")))
                //.andExpect(jsonPath("$[1].title", Matchers.is("one neight at call center")))
                .andExpect(content().json(jsonObject));
    }

    @Test
    public void shouldGetproductById() throws Exception {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Presuure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);
        
        jsonObject = mapper.writeValueAsString(product);
        when(repository.getOne(1)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.is("ADCD")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Two States")))
                .andExpect(content().json(jsonObject));
    }
    @Test
    public void addProduct() throws Exception {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Pressure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);
        when(repository.save(product)).thenReturn(product);
        jsonObject = mapper.writeValueAsString(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/addProduct")
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
    public void shouldUpdateProduct() throws Exception {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Pressure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);
        when(repository.save(product)).thenReturn(product);
        jsonObject = mapper.writeValueAsString(product);
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProduct")
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
    public void shouldDeleteProduct() throws Exception {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Pressure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);
        jsonObject = mapper.writeValueAsString(product);
        doNothing().when(repository).delete(product);
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProduct")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(repository, times(1)).delete(product);
    }



}