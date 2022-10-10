package com.in28minutes.microservices.apigateway.configuration;


import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
    {
        Function<PredicateSpec, Buildable<Route>> routeFunction
                = p -> p.path("/get") //if the request comes to /get
                .filters(f -> f.addRequestHeader("MyHeader", "MyUri"))
                .uri("http://httpbin.org:80"); //will direct to this uri

        return builder.routes()
                .route(routeFunction)
                .route(p -> p.path("/currency-exchange/**") //starts with currency-exchange
                        .uri("lb://currency-exchange")) //wil talk to eureka and will add in api-gateway and will add load balance

                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))

                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))

                .build();
    }
}
