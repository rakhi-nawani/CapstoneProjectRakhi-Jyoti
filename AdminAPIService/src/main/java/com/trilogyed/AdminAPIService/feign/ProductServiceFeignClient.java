package com.trilogyed.AdminAPIService.feign;

import com.trilogyed.AdminAPIService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceFeignClient {
    @GetMapping("/products")
    public List<Product> getAllProducts();
    @GetMapping ("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById ( @PathVariable Integer id);

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct ( @RequestBody Product product) ;

    @PutMapping("/updateProduct")
    public Product updateProduct ( @RequestBody Product product) ;

    @DeleteMapping ("/deleteProduct")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteProduct( @RequestBody Product product) ;

    @GetMapping ("/products/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductByInventory ( @PathVariable Integer inventory);

}
