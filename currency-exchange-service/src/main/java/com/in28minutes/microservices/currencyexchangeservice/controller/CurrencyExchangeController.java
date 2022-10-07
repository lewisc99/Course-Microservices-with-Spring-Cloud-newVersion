package com.in28minutes.microservices.currencyexchangeservice.controller;


import com.in28minutes.microservices.currencyexchangeservice.models.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {


    @Autowired
    private Environment environment;


    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from, @PathVariable String to
    )
    {

        String port = environment.getProperty("local.server.port");

        return new CurrencyExchange(from,to, BigDecimal.valueOf(50),port);

    }
}
