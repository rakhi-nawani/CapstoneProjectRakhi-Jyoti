package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.domain.*;
import com.trilogyed.retailedgeservice.feign.CustomerServiceFeignClient;
import com.trilogyed.retailedgeservice.feign.InvoiceClient;
import com.trilogyed.retailedgeservice.feign.LevelUpClient;
import com.trilogyed.retailedgeservice.feign.ProductServiceFeignClient;
import com.trilogyed.retailedgeservice.model.CustomerInvoice;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    private ServiceLayer service;
    private CustomerServiceFeignClient customerClient;
    private InvoiceClient invoiceClient;
    private ProductServiceFeignClient productClient;
    private LevelUpClient levelUpClient;

    @Before
    public void setUp() throws Exception {
        setUpCustomerServiceMock();
        setUpInvoicecrudserviceMock();
        setUpProductServiceMock();
        setUpLevelupcrudserviceMock();
        service = new ServiceLayer(customerClient, invoiceClient, levelUpClient, productClient);
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
        invoiceClient = mock(InvoiceClient.class);
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
        levelUpClient = mock(LevelUpClient.class);
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
    public void shouldGetInvoiceById() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        Item item = new Item(1,1, 214, 1, new BigDecimal(52.99));
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        invoice.setItems(itemList);
        CustomerInvoice ci = new CustomerInvoice();
        ci.setCustomerId(100);
        ci.setItems(itemList);
        ci.setPurchaseDate(LocalDate.of(2019,11,30));
        ci.setPoints(50);
        CustomerInvoice invoiceIExpect = service.getInvoicebyId(1);

        assertEquals(invoiceIExpect, ci);

    }

    @Test
    public void getInvoicebyCustomerIdId() {

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        Item item = new Item(1,1, 214, 1, new BigDecimal(52.99));
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        invoice.setItems(itemList);
        CustomerInvoice ci = new CustomerInvoice();
        ci.setCustomerId(100);
        ci.setItems(itemList);
        ci.setPurchaseDate(LocalDate.of(2019,11,30));
        ci.setPoints(50);
        CustomerInvoice invoiceIExpect = (CustomerInvoice) service.getInvoicebyCustomerIdId(100);

        assertEquals(invoiceIExpect, ci);


    }

    @Test
    public void deleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        service.deleteInvoice(invoice);

        assertNull( invoice);
    }

    @Test
    public void shouldUpdateInvoice() {
            Invoice invoice = new Invoice();
            invoice.setInvoiceId(1);
            invoice.setCustomerId(100);
            Item item = new Item(1,1, 214, 1, new BigDecimal(52.99));
            List<Item> itemList = new ArrayList<>();
            itemList.add(item);
            invoice.setPurchaseDate(LocalDate.of(2019,11,30));
            invoice.setItems(itemList);
            CustomerInvoice ci = new CustomerInvoice();
            ci.setCustomerId(100);
            ci.setItems(itemList);
            ci.setPurchaseDate(LocalDate.of(2019,11,30));
            ci.setPoints(50);
            CustomerInvoice invoiceIExpect = invoiceIExpect = service.updateInvoice(invoice);

            assertEquals(invoiceIExpect, invoice);

        }

    @Test
    public void getLevelupPointsbyCustomerId() {

    }

    @Test
    public void purchaceProduct() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        Item item = new Item(1,1, 214, 1, new BigDecimal(52.99));
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        invoice.setItems(itemList);
        CustomerInvoice ci = new CustomerInvoice();
        ci.setCustomerId(100);
        ci.setItems(itemList);
        ci.setPurchaseDate(LocalDate.of(2019,11,30));
        ci.setPoints(50);
        CustomerInvoice invoiceIExpect = invoiceIExpect = service.purchaceProduct(4, 100, 12);

        assertEquals(invoiceIExpect, invoice);

    }

    @Test
    public void calcualtePoints() {
        int LevelUpoints = 10;
        double orderTotal = 50;
        int customerId = 201;
        int pointsIGot = service.calcualtePoints(201, BigDecimal.valueOf(50) );
        assertEquals(LevelUpoints, pointsIGot);
    }

    @Test
    public void getProductsbyInvoiceId() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        Item item = new Item(1,1, 214, 1, new BigDecimal(52.99));
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        invoice.setItems(itemList);
        CustomerInvoice ci = new CustomerInvoice();
        ci.setCustomerId(100);
        ci.setItems(itemList);
        ci.setPurchaseDate(LocalDate.of(2019,11,30));
        ci.setPoints(50);
        List<Item> listIExpect = service.getProductsbyInvoiceId(1);

        assertEquals(listIExpect, itemList);

    }

    @Test
    public void submitInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        Item item = new Item(1,1, 214, 1, new BigDecimal(52.99));
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        invoice.setItems(itemList);
        CustomerInvoice ci = new CustomerInvoice();
        ci.setCustomerId(100);
        ci.setItems(itemList);
        ci.setPurchaseDate(LocalDate.of(2019,11,30));
        ci.setPoints(50);
        CustomerInvoice invoiceIExpect = invoiceIExpect = service.submitInvoice(invoice);

        assertEquals(invoiceIExpect, invoice);
    }

    @Test
    public void addViewModel() {

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
       invoice.setPurchaseDate(LocalDate.of(2019,11,30));
        Item item = new Item(1,1, 214, 1, new BigDecimal(52.99));
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        invoice.setItems(itemList);
        CustomerInvoice ci = new CustomerInvoice();
        ci.setCustomerId(100);
        ci.setItems(itemList);
        ci.setPurchaseDate(LocalDate.of(2019,11,30));
        ci.setPoints(50);
        CustomerInvoice invoiceIExpect = invoiceIExpect = service.addViewModel(invoice);

        assertEquals(invoiceIExpect, invoice);

    }

    }
}