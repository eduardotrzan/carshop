package com.carshop.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.carshop.service.config.CarshopServiceConfig;

@Configuration
@ComponentScan(basePackages = "com.carshop.controller")
@Import({ CarshopServiceConfig.class })
public class CarshopControllerConfig {


}
