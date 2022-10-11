package com.in28minutes.microservices.currencyexchangeservice.controller;


import com.in28minutes.microservices.currencyexchangeservice.models.CurrencyExchange;
import com.in28minutes.microservices.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from, @PathVariable String to
    )
    {

        logger.info("RetrieveExchangeValue called with {} to {}", from,to);

        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from,to);
        if (currencyExchange == null)
        {
            throw new RuntimeException("Unable to find data from:  " + from + " to: " + to);
        }
        currencyExchange.setEnvironment(port);
        return currencyExchange;

    }
}
