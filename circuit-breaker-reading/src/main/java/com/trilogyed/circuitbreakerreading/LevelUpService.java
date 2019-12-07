package com.trilogyed.circuitbreakerreading;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class LevelUpService {

    private final RestTemplate restTemplate;

    public LevelUpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public String readingList() {
        URI uri = URI.create("http://localhost:7001");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Level-Up Service is down, Please try again Later";
    }
}
