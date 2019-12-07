package com.trilogyed.AdminAPIService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.AdminAPIService.model.Customer;
import com.trilogyed.AdminAPIService.model.Invoice;
import com.trilogyed.AdminAPIService.model.Level;
import com.trilogyed.AdminAPIService.model.Product;
import com.trilogyed.AdminAPIService.service.AdminApiService;
import org.junit.Before;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminApiController.class)
public class AdminApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AdminApiService service;

    private ObjectMapper mapper = new ObjectMapper();
    private String jsonObject;

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void shouldGetAllCustomers() throws Exception {

        List<Customer> customerList = Arrays.asList(
                new Customer("John", "Ray", "75 Kristin cr", "Schaumburg", "60195", "john@gmail.com", "2245781671" ),
                new Customer("Kiti", "Ray", "75 Kristin cr", "Schaumburg", "60195", "kiti@gmail.com", "2255781671" )
        );
        jsonObject = mapper.writeValueAsString(customerList);
        when(service.getAllCustomers()).thenReturn(customerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
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
        when(service.getCustomerById(1)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
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
        when(service.saveCustomer(customer)).thenReturn(customer);
        jsonObject = mapper.writeValueAsString(customer);
        mockMvc.perform(MockMvcRequestBuilders.post("/addCustomer")
                .contentType(APPLICATION_JSON)
                .content(jsonObject)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(jsonObject))
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
        when(service.updateCustomer(customer)).thenReturn(customer);
        jsonObject = mapper.writeValueAsString(customer);
        mockMvc.perform(MockMvcRequestBuilders.put("/updateCustomer")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(content().json(jsonObject))
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
        doNothing().when(service).deleteCustomer(customer);
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCustomer")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isNoContent());
       // verify(service, times(1)).deleteCustomer(customer);
    }

    @Test
    public void shouldGetAllProducts() throws Exception {

        List<Product> productList = Arrays.asList(
                new Product("TV", "this ia an OLD TV", 2000.99, 1500.00 , 5 ),
                new Product("Instant Pot", "Electrical Presuure Cooker", 150.55, 125.00, 4 )
        );
        jsonObject = mapper.writeValueAsString(productList);
        when(service.getAllProducts()).thenReturn(productList);
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
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
        when(service.getProductById(1)).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
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
        when(service.saveProduct(product)).thenReturn(product);
        jsonObject = mapper.writeValueAsString(product);
        mockMvc.perform(MockMvcRequestBuilders.post("/addProduct")
                .contentType(APPLICATION_JSON)
                .content(jsonObject)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(jsonObject))
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
        when(service.updateProduct(product)).thenReturn(product);
        jsonObject = mapper.writeValueAsString(product);
        mockMvc.perform(MockMvcRequestBuilders.put("/updateProduct")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(content().json(jsonObject))
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
        doNothing().when(service).deleteProduct(product);
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProduct")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(service, times(1)).deleteProduct(product);
    }

    // LEVELUP//


    @Test
    public void getAllLevels() throws Exception {
        List<Level> levelList = Arrays.asList(
                new Level(1, 100,250, LocalDate.of(2019,11,30)),
                new Level(1, 100,250, LocalDate.of(2019,11,30))
        );
        jsonObject = mapper.writeValueAsString(levelList);
        when(service.getAllLevelUps()).thenReturn(levelList);
        mockMvc.perform(MockMvcRequestBuilders.get("/levels")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }

    @Test
    public void shouldGetLevelById() throws Exception {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        jsonObject = mapper.writeValueAsString(level);
        when(service.getLevelById(1)).thenReturn(level);
        mockMvc.perform(MockMvcRequestBuilders.get("/level/id/1").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }


    @Test
    public void addLevel() throws Exception {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        when(service.saveLevel(level)).thenReturn(level);
        jsonObject = mapper.writeValueAsString(level);
        mockMvc.perform(MockMvcRequestBuilders.post("/levels")
                .contentType(APPLICATION_JSON)
                .content(jsonObject)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(jsonObject))
                .andExpect(status().isCreated());
    }


    @Test
    public void shouldUpdateLevel() throws Exception {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        when(service.updateLevel(level)).thenReturn(level);
        jsonObject = mapper.writeValueAsString(level);
        mockMvc.perform(MockMvcRequestBuilders.put("/level")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(content().json(jsonObject))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldDeleteLevel() throws Exception {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        doNothing().when(service).deleteLevel(level);
        jsonObject = mapper.writeValueAsString(level);
        mockMvc.perform(MockMvcRequestBuilders.delete("/level")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(service, times(1)).deleteLevel(level);
    }

    //Invoice//

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
    public void shouldGetInvoiceById() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        jsonObject = mapper.writeValueAsString(invoice);
        when(service.geInvoiceById(1)).thenReturn(invoice);
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
        when(service.saveInvoice(invoice)).thenReturn(invoice);
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
        when(service.updateInvoice(invoice)).thenReturn(invoice);
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
        doNothing().when(service).deleteInvoice(invoice);
        jsonObject = mapper.writeValueAsString(invoice);
        mockMvc.perform(MockMvcRequestBuilders.delete("/invoice")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(service, times(1)).deleteInvoice(invoice);
    }

}