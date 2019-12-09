package com.trilogyed.AdminAPIService.feign;

import com.trilogyed.AdminAPIService.model.Level;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "level-up-service")
public interface LevelupcrudserviceClient {
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

}
