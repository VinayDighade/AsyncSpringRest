package com.example.asyncrest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "https://restcountries.com/v2/" , name="CountryFeignClient")
public interface CountryFeignClient {

//https://restcountries.com/v3.1/region/europe
    @GetMapping(value = "region/{europe}?fields=name", consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    List<Country> getCountriesByRegion(@PathVariable(name="europe") String europe);

    @GetMapping(value ="lang/{fr}?fields=name", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Country> getCountriesByLanguage(@PathVariable(name="fr") String fr);
}
