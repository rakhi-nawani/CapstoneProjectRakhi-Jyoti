package com.trilogyed.levelupcrudservice.dao;

import com.trilogyed.levelupcrudservice.dto.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelUpRepository extends JpaRepository<Level, Integer> {
}
