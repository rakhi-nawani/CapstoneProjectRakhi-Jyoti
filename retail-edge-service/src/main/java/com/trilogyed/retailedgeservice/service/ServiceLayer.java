package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.domain.*;
import com.trilogyed.retailedgeservice.feign.CustomerServiceFeignClient;
import com.trilogyed.retailedgeservice.feign.InvoiceClient;
import com.trilogyed.retailedgeservice.feign.LevelUpClient;
import com.trilogyed.retailedgeservice.feign.ProductServiceFeignClient;
import com.trilogyed.retailedgeservice.model.CustomerInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer {

    public ServiceLayer serviceLayer;

    CustomerServiceFeignClient customerServiceFeignClient;
    InvoiceClient invoiceClient;
    LevelUpClient levelUpClient;
    ProductServiceFeignClient productServiceFeignClient;

    BigDecimal orderTotal= BigDecimal.valueOf(0);

    @Autowired
    public ServiceLayer(CustomerServiceFeignClient customerServiceFeignClient, InvoiceClient invoiceClient, LevelUpClient levelUpClient, ProductServiceFeignClient productServiceFeignClient) {
        this.customerServiceFeignClient = customerServiceFeignClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productServiceFeignClient = productServiceFeignClient;
    }

    public CustomerInvoice getInvoicebyId(int invoiceId){
       return addViewModel(invoiceClient.getInvoice(invoiceId));
    }

    public List<CustomerInvoice> getInvoicebyCustomerIdId(int customerId) {
        List<CustomerInvoice> customerInvoices = new ArrayList<>();
        List<Invoice> invoices = invoiceClient.findInvoiceByCustomerId(customerId);
        for (Invoice invoice = invoices) {
            if (invoice == null) {
                throw new IllegalArgumentException("This Customer is not available in Database");
            }
            customerInvoices.add(addViewModel(invoice));

            return customerInvoices;
        }
    }

    public void deleteInvoice(Invoice invoice){
            invoiceClient.deleteInvoice(invoice);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public CustomerInvoice updateInvoice( @RequestBody Invoice invoice) {
        return addViewModel(invoiceClient.updateInvoice(invoice));
    }

    public Customer findCustomer(int id) {
        Customer customer = customerServiceFeignClient.getCustomerById(id);
        if(customer == null){
            throw new IllegalArgumentException("This Customer is not available in Database");
        }
        return customer;
    }

    public int getLevelupPointsbyCustomerId(int customerId){
     return levelUpClient.findLevelPointsByCustomerId(customerId);
    }

    public List<Product> searchInventory(){
        return productServiceFeignClient.getAllProducts();
    }

    public CustomerInvoice purchaceProduct(int quantity, int customerId, int productId){
        Invoice invoice = new Invoice();
        invoice.setCustomerId(customerId);
        invoice.setPurchaseDate(LocalDate.now());
        invoiceClient.addInvoice(invoice);
        Item item = new Item();
        item.setInvoiceId(invoice.getInvoiceId());
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setUnitPrice(productServiceFeignClient.getProductById(productId).getList_price());
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        CustomerInvoice customerInvoice = new CustomerInvoice();
        customerInvoice.setCustomerId(customerId);
        customerInvoice.setItems(itemList);
        customerInvoice.setPurchaseDate(LocalDate.now());
        customerInvoice.setOrderTotal(serviceLayer.orderTotal);
        customerInvoice.setPoints(serviceLayer.calcualtePoints(customerId, serviceLayer.orderTotal));
        return customerInvoice;
    }


    //Helper methods
    public int calcualtePoints(int customerId, BigDecimal orderTotal){
      int levelUpPoints= 0;
<<<<<<< HEAD
      Level levelup = new Level();
      int levelPoints = levelUpClient.findLevelPointsByCustomerId(customerId);
      if(levelPoints< 50) {
          levelup.setCustomerId(customerId);
          levelup.setMemberDate(LocalDate.now());
          levelup.setPoints(0);
      } if (orderTotal.compareTo(BigDecimal.valueOf(50)) == 1){
          int newPoints = orderTotal.intValue()/50;
          levelup.setPoints(levelup.getPoints()+ (10*50));
        }

=======
      if(orderTotal.compareTo(BigDecimal.valueOf(50)) == -1) {
          levelUpPoints = 0;
      } else if ( orderTotal.compareTo(BigDecimal.valueOf(50)) == 0 && orderTotal.compareTo(BigDecimal.valueOf(50)) == 1)
            {
                levelUpPoints = 10;
            }
      else  if(orderTotal.compareTo(orderTotal.add(BigDecimal.valueOf(50))) == 1);{
           levelUpPoints = levelUpPoints+10;
           }
>>>>>>> d1d609c8af70355b0e61999b865e04305779ec10
           return levelUpPoints;
      }


    public List<Item> getProductsbyInvoiceId(int invoiceId){
        List<Product>  allProductList = productServiceFeignClient.getAllProducts();
          List<Item> invoiceItemList =  invoiceClient.getInvoice(invoiceId).getItems();
            return invoiceItemList;
        }

    public CustomerInvoice submitInvoice(Invoice invoice){
       return addViewModel(invoiceClient.addInvoice(invoice));
    }

    private BigDecimal orderTotal(List<Item> itemList) {
        BigDecimal orderTotal = new BigDecimal("0");
        itemList.stream().forEach(invoiceItems -> {
            orderTotal.add(invoiceItems.getUnitPrice().multiply(new BigDecimal(invoiceItems.getQuantity())));
        });
        return orderTotal;
    }

    public CustomerInvoice addViewModel(Invoice invoice){
        CustomerInvoice customerInvoice = new CustomerInvoice();
        customerInvoice.setCustomerId(invoice.getCustomerId());
        customerInvoice.setItems(invoice.getItems());
        customerInvoice.setPurchaseDate(invoice.getPurchaseDate());
       // customerInvoice.setPoints(serviceLayer.calcualtePoints(invoice.getCustomerId(), serviceLayer.orderTotal()));
        return customerInvoice;
    }

    public Object getAllInvoice() {

        List<Invoice> InvoiceList = invoiceClient.getAllInvoices();
        return InvoiceList ;

    }
}
