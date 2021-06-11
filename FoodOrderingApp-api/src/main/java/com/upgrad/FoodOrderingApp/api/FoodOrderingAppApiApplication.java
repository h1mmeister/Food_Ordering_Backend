package com.upgrad.FoodOrderingApp.api;


import com.upgrad.FoodOrderingApp.service.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServiceConfiguration.class)
public class FoodOrderingAppApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodOrderingAppApiApplication.class, args);
    }
}

