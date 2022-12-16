package com.example.asyncrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("async")
public class AsyncController {

    private final AsyncService service;

    @Autowired
    public AsyncController(AsyncService service) {
        this.service = service;
    }

    @GetMapping("/getAllEuropeanFrenchSpeakingCountries")
    public List<String> getAllEuropeanFrenchSpeakingCountries() {
       return service.getAllEuropeanFrenchSpeakingCountries();

    }
}
