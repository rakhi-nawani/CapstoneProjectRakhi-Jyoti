package com.trilogyed.levelupcrudservice.dao;

import com.trilogyed.levelupcrudservice.dto.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelUpRepository extends JpaRepository<Level, Integer> {
   int findLevelByCustomerId(Integer customerId);
}

