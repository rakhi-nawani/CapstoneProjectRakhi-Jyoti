package com.trilogyed.AdminAPIService.service;

import com.trilogyed.AdminAPIService.feign.CustomerServiceFeignClient;
import com.trilogyed.AdminAPIService.feign.InvoicecrudserviceClient;
import com.trilogyed.AdminAPIService.feign.LevelupcrudserviceClient;
import com.trilogyed.AdminAPIService.feign.ProductServiceFeignClient;
import com.trilogyed.AdminAPIService.model.Customer;
import com.trilogyed.AdminAPIService.model.Invoice;
import com.trilogyed.AdminAPIService.model.Level;
import com.trilogyed.AdminAPIService.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


public class AdminApiServiceTest {
    private AdminApiService service;
    private CustomerServiceFeignClient customerClient;
    private InvoicecrudserviceClient invoiceClient;
    private ProductServiceFeignClient productClient;
    private LevelupcrudserviceClient levelUpClient;

    @Before
    public void setUp() throws Exception {
        setUpCustomerServiceMock();
        setUpInvoicecrudserviceMock();
        setUpProductServiceMock();
        setUpLevelupcrudserviceMock();
        service = new AdminApiService(customerClient, invoiceClient, productClient, levelUpClient);
    }

    private void setUpCustomerServiceMock() {
        customerClient = mock(CustomerServiceFeignClient.class);
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        doReturn(customer).when(customerClient).getCustomerById(1);
        doReturn(customerList).when(customerClient).getAllCustomers();
        doReturn(customer).when(customerClient).addCustomer(customer);
        doReturn(customer).when(customerClient).updateCustomer(customer);
        doNothing().when(customerClient).deleteCustomer(customer);

    }
    private void setUpProductServiceMock() {
        productClient = mock(ProductServiceFeignClient.class);
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Presuure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        doReturn(product).when(productClient).getProductById(1);
        doReturn(productList).when(productClient).getAllProducts();
        doReturn(product).when(productClient).addProduct(product);
        doReturn(product).when(productClient).updateProduct(product);
        doNothing().when(productClient).deleteProduct(product);
    }

    private void setUpInvoicecrudserviceMock() {
        invoiceClient = mock(InvoicecrudserviceClient.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceClient).getInvoice(1);
        doReturn(invoiceList).when(invoiceClient).getAllInvoices();
        doReturn(invoice).when(invoiceClient).addInvoice(invoice);
        doReturn(invoice).when(invoiceClient).updateInvoice(invoice);
        doNothing().when(invoiceClient).deleteInvoice(invoice);
    }

    private void setUpLevelupcrudserviceMock() {
        levelUpClient = mock(LevelupcrudserviceClient.class);
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));

        List<Level> levelList = new ArrayList<>();
        levelList.add(level);

        doReturn(level).when(levelUpClient).getLevel(1);
        doReturn(levelList).when(levelUpClient).getAllLevels();
        doReturn(level).when(levelUpClient).addLevel(level);
        doReturn(level).when(levelUpClient).updateLevel(level);
        doNothing().when(levelUpClient).deleteLevel(level);

    }

    @Test
    public void shouldGetAllCustomer() {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        List<Customer> listIExpect = service.getAllCustomers();

        assertEquals(listIExpect, customerList);
    }

    @Test
    public void shouldGetCustomerById() {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671");

        Customer customerIExpect = service.getCustomerById(1);

        assertEquals(customerIExpect, customer);
    }

    @Test
    public void shouldCreateCustomerWithProvidedInfo() {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671");

        Customer customerIExpect = service.saveCustomer(customer);

        assertEquals(customerIExpect, customer);
    }

    @Test
    public void shouldUpdateCustomerWithProvidedInfo() {
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Kiti");
        customer.setLast_name("Ray");
        customer.setStreet("75 Kristin cr");
        customer.setCity("Schaumburg");
        customer.setZip("60195");
        customer.setEmail("kiti@gmail.com");
        customer.setPhone("2255781671");

        Customer customerIExpect = service.updateCustomer(customer);

        assertEquals(customerIExpect, customer);

    }

    @Test
    public void shouldDeleteGivenCustomer() {
        Customer customer = new Customer(1, "John", "Ray", "75 Kristin cr", "Schaumburg", "60195", "kiti@gmail.com", "2255781671");
        service.deleteCustomer(customer);
        assertNull(customer);
    }


    @Test
    public void shouldGetAllProduct() {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Presuure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        List<Product> listIExpect = service.getAllProducts();

        assertEquals(listIExpect, productList);
    }

    @Test
    public void shouldGetProductById() {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Presuure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);

        Product productIExpect = service.getProductById(1);

        assertEquals(productIExpect, product);

    }

    @Test
    public void shouldCreateProductWithProvidedInfo() {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Presuure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);

        Product productIExpect = service.saveProduct(product);

        assertEquals(productIExpect, product);
    }

    @Test
    public void shouldUpdateProductWithProvidedInfo() {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Presuure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);
        Product productIExpect = service.updateProduct(product);

        assertEquals(productIExpect, product);

    }
    @Test
    public void shouldDeleteProductWithProvidedInfo() {
        Product product = new Product();
        product.setProduct_id(1);
        product.setProduct_name("Instant Pot");
        product.setProduct_description("Electrical Presuure Cooker");
        product.setList_price(150.55);
        product.setUnit_cost(125.00);
        product.setInventory(4);
        service.deleteProduct(product);

        assertNull( product);

    }

    //LevelUp//


    @Test
    public void shouldGetAllLevel() {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        List<Level> levelList = new ArrayList<>();
        levelList.add(level);

        List<Level> listIExpect = service.getAllLevelUps();

        assertEquals(listIExpect, levelList);
    }

    @Test
    public void shouldGetLevelById() {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));

        Level levelIExpect = service.getLevelById(1);

        assertEquals(levelIExpect, level);

    }

    @Test
    public void shouldCreateLevelWithProvidedInfo() {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        Level levelIExpect = service.saveLevel(level);

        assertEquals(levelIExpect, level);
    }

    @Test
    public void shouldUpdateLevelWithProvidedInfo() {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));

        Level levelIExpect = service.updateLevel(level);

        assertEquals(levelIExpect, level);

    }
    @Test
    public void shouldDeleteLevelWithProvidedInfo() {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        service.deleteLevel(level);

        assertNull( level);
    }

    //Invoice//

    @Test
    public void shouldGetAllInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        List<Invoice>  invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        List<Invoice> listIExpect = service.getAllInvoice();

        assertEquals(listIExpect, invoiceList);
    }
    @Test
    public void shouldGetInvoiceById() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));

        Invoice invoiceIExpect = service.geInvoiceById(1);

        assertEquals(invoiceIExpect, invoice);

    }

    @Test
    public void shouldCreateInvoiceWithProvidedInfo() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        Invoice invoiceIExpect = service.saveInvoice(invoice);

        assertEquals(invoiceIExpect, invoice);
    }

    @Test
    public void shouldUpdateInvoiceWithProvidedInfo() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        Invoice invoiceIExpect = service.updateInvoice(invoice);

        assertEquals(invoiceIExpect, invoice);

    }
    @Test
    public void shouldDeleteInvoiceWithProvidedInfo() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        service.deleteInvoice(invoice);

        assertNull( invoice);
    }

}