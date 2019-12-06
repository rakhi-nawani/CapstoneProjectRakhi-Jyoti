package com.trilogyed.AdminAPIService.service;

import com.trilogyed.AdminAPIService.frign.CustomerServiceFeignClient;
import com.trilogyed.AdminAPIService.frign.InvoicecrudserviceClient;
import com.trilogyed.AdminAPIService.frign.LevelupcrudserviceClient;
import com.trilogyed.AdminAPIService.frign.ProductServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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






}
