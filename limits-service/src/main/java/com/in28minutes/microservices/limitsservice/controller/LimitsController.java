package com.in28minutes.microservices.limitsservice.controller;


import com.in28minutes.microservices.limitsservice.configuration.Configuration;
import com.in28minutes.microservices.limitsservice.models.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;
    @GetMapping("/limits")
    public Limits retrieveLimits()
    {
        int minimum = configuration.getMinimum();
        int maximum = configuration.getMaximum();

        return new Limits(minimum,maximum);
    }
}
