package com.example.asyncrest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AsyncService {

    private final CountryFeignClient countryClient;

    @Autowired
    public AsyncService(CountryFeignClient countryClient) {
        this.countryClient = countryClient;
    }


    public List<String> getAllEuropeanFrenchSpeakingCountries() {
        log.info("getAllEuropeanFrenchSpeakingCountries() method called");

        log.info("1. getCountryListByLanguage() method called");
        CompletableFuture<List<Country>> countriesByLanguage = getCountryListByLanguage();
        log.info("2. getCountryListByRegion() method called");
        CompletableFuture<List<Country>> countriesByRegion = getCountryListByRegion();
        List<String> countries = new ArrayList<>();
        log.info("After Calling the Two Methods");
        try {
            countries = processData(countriesByLanguage.get(), countriesByRegion.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return countries;
    }

    private List<String> processData(List<Country> countriesByLanguage, List<Country> countriesByRegion) {
        log.info("3. processData() method called");
        List<String> europeanFrenchSpeakingCountries = countriesByLanguage.stream().map(Country::getName).collect(Collectors.toList());
        europeanFrenchSpeakingCountries.retainAll(countriesByRegion.stream().map(Country::getName).collect(Collectors.toList()));

        return europeanFrenchSpeakingCountries;
    }

    @Async
    public CompletableFuture<List<Country>> getCountryListByRegion() {
        log.info("getCountryListByRegion() rest api call to get Countries by europe region , StartTime = {}", LocalDateTime.now());
        log.info("getCountryListByRegion() Thread name ={}", Thread.currentThread().getName());
        List<Country> data = countryClient.getCountriesByRegion("europe");
        log.info("getCountryListByRegion() rest api call to get Countries by europe region, FinishTime = {}", LocalDateTime.now());
        return CompletableFuture.completedFuture(data);
    }

    @Async
    public CompletableFuture<List<Country>> getCountryListByLanguage() {
        log.info("getCountryListByLanguage() rest api call to get Countries by english language , StartTime = {}", LocalDateTime.now());
        log.info("getCountryListByLanguage() Thread name ={}", Thread.currentThread().getName());
        try {
            Thread.sleep(5 * 1000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Country> data = countryClient.getCountriesByLanguage("fr");
        log.info("getCountryListByLanguage() rest api call to by english language, FinishTime = {}", LocalDateTime.now());
        return CompletableFuture.completedFuture(data);
    }


}
