package com.trilogyed.invoicecrudservice.dao;


import com.trilogyed.invoicecrudservice.dto.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}

