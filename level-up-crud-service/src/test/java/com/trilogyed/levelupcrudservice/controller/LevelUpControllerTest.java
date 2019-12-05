package com.trilogyed.levelupcrudservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.levelupcrudservice.dao.LevelUpRepository;
import com.trilogyed.levelupcrudservice.dto.Level;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LevelUpController.class)
public class LevelUpControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LevelUpRepository repo;

    private ObjectMapper mapper = new ObjectMapper();
    private String jsonObject;

    @Test
    public void getAllLevels() throws Exception {
        List<Level> levelList = Arrays.asList(
                new Level(1, 100,250, LocalDate.of(2019,11,30)),
                new Level(1, 100,250, LocalDate.of(2019,11,30))
        );
        jsonObject = mapper.writeValueAsString(levelList);
        when(repo.findAll()).thenReturn(levelList);
        mockMvc.perform(MockMvcRequestBuilders.get("/levels")
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }

    @Test
    public void shouldGetLevelById() throws Exception {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        jsonObject = mapper.writeValueAsString(level);
        when(repo.getOne(1)).thenReturn(level);
        mockMvc.perform(MockMvcRequestBuilders.get("/level/id/1").accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonObject));
    }


    @Test
    public void addLevel() throws Exception {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        when(repo.save(level)).thenReturn(level);
        jsonObject = mapper.writeValueAsString(level);
        mockMvc.perform(MockMvcRequestBuilders.post("/levels")
                .contentType(APPLICATION_JSON)
                .content(jsonObject)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(jsonObject))
                .andExpect(status().isCreated());
    }


    @Test
    public void shouldUpdateLevel() throws Exception {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        when(repo.save(level)).thenReturn(level);
        jsonObject = mapper.writeValueAsString(level);
        mockMvc.perform(MockMvcRequestBuilders.put("/level")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(content().json(jsonObject))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldDeleteLevel() throws Exception {
        Level level = new Level();
        level.setLevelUpId(1);
        level.setCustomerId(100);
        level.setPoints(250);
        level.setMemberDate(LocalDate.of(2019,11,30));
        doNothing().when(repo).delete(level);
        jsonObject = mapper.writeValueAsString(level);
        mockMvc.perform(MockMvcRequestBuilders.delete("/level")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonObject))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(repo, times(1)).delete(level);
    }





}