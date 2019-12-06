package com.trilogyed.levelupcrudservice.controller;

import com.trilogyed.levelupcrudservice.dao.LevelUpRepository;
import com.trilogyed.levelupcrudservice.dto.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LevelUpController {

    @Autowired
    LevelUpRepository repo;

   @RequestMapping(value = "/levels", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Level> getAllLevels() {
        return repo.findAll();
    }


    @RequestMapping(value = "/levels", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Level addLevel(@RequestBody Level level) {
        return repo.save(level);
    }

    @RequestMapping(value = "/level/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Level getLevel(@PathVariable int id) {
        return repo.getOne(id);
    }

    @RequestMapping(value = "/level", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Level updateLevel( @RequestBody Level level) {
        return repo.save(level);
    }

    @RequestMapping(value = "/level", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevel(@RequestBody Level level) {
        repo.delete(level);
    }


}
