package com.in28minutes.microservices.currencyexchangeservice.controller;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardCodedResponse")
//    @RateLimiter(name="default")
    @Bulkhead(name="sample-api")
    // in 10s -> only allowed 1000 calls to the same api
    public String sampleApi()
    {
        logger.info("Sample Api Call received");
//        ResponseEntity<String> forEntity =  new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//
//
//        return forEntity.getBody();

        return "Sample-api";
    }

    public String hardCodedResponse(Exception ex)
    {
        return "fallback-response";
    }


}
