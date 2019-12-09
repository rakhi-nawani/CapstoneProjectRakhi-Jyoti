package com.trilogyed.levelupcrudservice.controller;

import com.trilogyed.levelupcrudservice.dao.LevelUpRepository;
import com.trilogyed.levelupcrudservice.dto.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = {"levels"})
public class LevelUpController {

    @Autowired
    LevelUpRepository repo;

   @RequestMapping(value = "/levels", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Level> getAllLevels() {
        return repo.findAll();
    }

   // @CachePut(key = "#result.getlevelUpId()")
    @RequestMapping(value = "/levels", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Level addLevel(@RequestBody Level level) {
        return repo.save(level);
    }

    @Cacheable
    @RequestMapping(value = "/level/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Level getLevel(@PathVariable int id) {
        return repo.getOne(id);
    }

    @CacheEvict(key = "#rsvp.getId()")
    @RequestMapping(value = "/level", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Level updateLevel( @RequestBody Level level) {
        return repo.save(level);
    }

    @CacheEvict
    @RequestMapping(value = "/level", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevel(@RequestBody Level level) {
        repo.delete(level);
    }

    // Custom Method
    @CachePut(key = "#result.getId()")
    @RequestMapping(value = "/level/customerId/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public int findLevelPointsByCustomerId(@PathVariable int customerId) {
        return repo.findLevelByCustomerId(customerId);
    }

}
