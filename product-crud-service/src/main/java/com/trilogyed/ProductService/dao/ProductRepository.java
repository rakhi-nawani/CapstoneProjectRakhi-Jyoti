package com.trilogyed.ProductService.dao;

import com.trilogyed.ProductService.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findProductByInventory( Integer inventory);
}
