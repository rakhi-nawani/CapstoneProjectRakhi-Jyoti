package com.trilogyed.retailedgeservice.service;

import com.trilogyed.retailedgeservice.domain.Invoice;
import com.trilogyed.retailedgeservice.domain.Item;
import com.trilogyed.retailedgeservice.domain.Level;
import com.trilogyed.retailedgeservice.domain.Product;
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

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceLayer {

    public ServiceLayer serviceLayer;

    CustomerServiceFeignClient customerServiceFeignClient;
    InvoiceClient invoiceClient;
    LevelUpClient levelUpClient;
    ProductServiceFeignClient productServiceFeignClient;

    double orderTotal= 0;

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

    public List<CustomerInvoice> getInvoicebyCustomerIdId(int customerId){

        return null;
    }

    public void deleteInvoice(Invoice invoice){
            invoiceClient.deleteInvoice(invoice);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public CustomerInvoice updateInvoice( @RequestBody Invoice invoice) {
        return addViewModel(invoiceClient.updateInvoice(invoice));
    }

    public int getLevelupPointsbyCustomerId(int customerId){
     return levelUpClient.findLevelPointsByCustomerId(customerId);
    }


    public CustomerInvoice purchaceProduct(int quantity, int customerId, int productId){
        CustomerInvoice customerInvoice = new CustomerInvoice();
        customerInvoice.setCustomerId(customerId);
        customerInvoice.setItems(invoiceClient.getInvoice(customerInvoice.getInvoiceId()).getItems());
        customerInvoice.setPurchaseDate(LocalDate.now());
        orderTotal = quantity* productServiceFeignClient.getProductById(productId).getList_price();
        customerInvoice.setPoints(serviceLayer.calcualtePoints());
        return customerInvoice;
    }


    //Helper methods
    public int calcualtePoints(){
      int levelUpPoints= 0;
      if(orderTotal<50) {
          levelUpPoints = 0;
      } else if ( orderTotal >= 50 && orderTotal < 100)
            {
                levelUpPoints = 10;
            }
      else  if(orderTotal > orderTotal+50);{
           levelUpPoints = levelUpPoints+10;
           }
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


    public CustomerInvoice addViewModel(Invoice invoice){
        CustomerInvoice customerInvoice = new CustomerInvoice();
        customerInvoice.setCustomerId(invoice.getCustomerId());
        customerInvoice.setItems(invoice.getItems());
        customerInvoice.setPurchaseDate(invoice.getPurchaseDate());
        customerInvoice.setItems(invoice.getItems());
        customerInvoice.setPoints(serviceLayer.calcualtePoints());
        return customerInvoice;
    }

    }
