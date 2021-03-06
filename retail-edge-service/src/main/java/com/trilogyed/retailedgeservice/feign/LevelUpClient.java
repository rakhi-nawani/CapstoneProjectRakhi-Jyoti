package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.domain.Level;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "level-up-service")
public interface LevelUpClient {

    @RequestMapping(value = "/levels", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Level> getAllLevels();


    @RequestMapping(value = "/levels", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Level addLevel(@RequestBody Level level);

    @RequestMapping(value = "/level/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Level getLevel(@PathVariable int id);

    @RequestMapping(value = "/level", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Level updateLevel( @RequestBody Level level);

    @RequestMapping(value = "/level", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevel(@RequestBody Level level);


    @RequestMapping(value = "/level/customerId/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public int findLevelPointsByCustomerId(@PathVariable int customerId);
}
