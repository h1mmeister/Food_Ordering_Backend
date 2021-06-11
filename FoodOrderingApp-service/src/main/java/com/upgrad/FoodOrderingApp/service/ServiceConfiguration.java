package com.upgrad.FoodOrderingApp.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.upgrad.FoodOrderingApp.service")
@EntityScan("com.upgrad.FoodOrderingApp.service.entity")
public class ServiceConfiguration {
}
