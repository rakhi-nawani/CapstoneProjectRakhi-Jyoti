package com.trilogyed.ProductService.controller;

import com.trilogyed.ProductService.dao.ProductRepository;
import com.trilogyed.ProductService.dto.Product;
import com.trilogyed.ProductService.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository repository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping ("/products/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById ( @PathVariable Integer id) {
        return repository.getOne(id);
    }

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct ( @RequestBody Product product) {
        return repository.save(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct ( @RequestBody Product product) {
        return repository.save(product);
    }

    @DeleteMapping ("/deleteProduct")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteProduct( @RequestBody Product product) {
        repository.delete(product);
    }

    @GetMapping ("/products/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductByInventory ( @PathVariable Integer inventory) {
        List<Product> returnVal = new ArrayList<>();
        returnVal.add( repository.findProductByInventory(inventory));
        if ( inventory  == null ) {
            throw new NotFoundException("There is no Product with inventory " + inventory);
        }
        return returnVal;
    }


}
