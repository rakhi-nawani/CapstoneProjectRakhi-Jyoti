package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ProductService")
public interface ProductServiceFeignClient {
    @GetMapping("/products")
    public List<Product> getAllProducts();

    @GetMapping ("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable Integer id);

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) ;

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) ;

    @DeleteMapping ("/deleteProduct")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestBody Product product) ;

    @GetMapping ("/products/invoiceId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductByinvoiceId(@PathVariable Integer invoiceId);
}
