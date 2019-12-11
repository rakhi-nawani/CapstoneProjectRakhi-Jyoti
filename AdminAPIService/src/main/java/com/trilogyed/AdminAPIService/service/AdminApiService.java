package com.trilogyed.AdminAPIService.service;

import com.trilogyed.AdminAPIService.feign.CustomerServiceFeignClient;
import com.trilogyed.AdminAPIService.feign.InvoicecrudserviceClient;
import com.trilogyed.AdminAPIService.feign.LevelupcrudserviceClient;
import com.trilogyed.AdminAPIService.feign.ProductServiceFeignClient;
import com.trilogyed.AdminAPIService.model.Customer;
import com.trilogyed.AdminAPIService.model.Invoice;
import com.trilogyed.AdminAPIService.model.Level;
import com.trilogyed.AdminAPIService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminApiService {

    private CustomerServiceFeignClient customerClient;
    private InvoicecrudserviceClient invoiceClient;
    private ProductServiceFeignClient productClient;
    private LevelupcrudserviceClient levelUpClient;

    @Autowired
    public AdminApiService(CustomerServiceFeignClient customerClient, InvoicecrudserviceClient invoiceClient, ProductServiceFeignClient productClient, LevelupcrudserviceClient levelUpClient){
            this.customerClient = customerClient;
            this.invoiceClient = invoiceClient;
            this.productClient = productClient;
            this.levelUpClient = levelUpClient;
    }


    public List<Customer> getAllCustomers(){

        List<Customer> customerList = customerClient.getAllCustomers();
        return customerList ;
    }

    public Customer getCustomerById(int id){

        Customer customer = customerClient.getCustomerById(id);
        return customer;
    }

    public Customer saveCustomer(Customer customer){

        return customerClient.addCustomer(customer)  ;  }

    public Customer updateCustomer(Customer customer){

        return customerClient.updateCustomer(customer)  ;  }


    public void deleteCustomer(Customer customer)  {
        customerClient.deleteCustomer(customer);
    }

    public List<Product> getAllProducts(){

        List<Product> productListList = productClient.getAllProducts();
        return productListList ;
    }

    public Product getProductById(int id){

        Product product = productClient.getProductById(id);
        return product;
    }

    public Product saveProduct(Product product){

        return productClient.addProduct(product)  ;  }

    public Product updateProduct(Product product){

        return productClient.updateProduct(product)  ;  }


    public void deleteProduct(Product product)  {
        productClient.deleteProduct(product);
    }


    // LevelPoint //

    public List<Level> getAllLevelUps(){

        List<Level> LevelUpList = levelUpClient.getAllLevels();
        return LevelUpList ;
    }

    public Level getLevelById(int id){

        Level level = levelUpClient.getLevel(id);
        return level;
    }

    public Level saveLevel(Level level){

        return levelUpClient.addLevel(level)  ;  }

    public Level updateLevel(Level level){

        return levelUpClient.updateLevel(level)  ;  }


    public void deleteLevel(Level level)  {
        levelUpClient.deleteLevel(level);
    }

    //Invoice

    public List<Invoice> getAllInvoice(){

        List<Invoice> InvoiceList = invoiceClient.getAllInvoices();
        return InvoiceList ;
    }

    public Invoice geInvoiceById(int id){

        Invoice invoice = invoiceClient.getInvoice(id);
        return invoice;
    }

    public Invoice saveInvoice(Invoice invoice){

        return invoiceClient.addInvoice(invoice)  ;  }

    public Invoice updateInvoice(Invoice invoice){

        return invoiceClient.updateInvoice(invoice)  ;  }


    public void deleteInvoice(Invoice invoice)  {
        invoiceClient.deleteInvoice(invoice);
    }
}
