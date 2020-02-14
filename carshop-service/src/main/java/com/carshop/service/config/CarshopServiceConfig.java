package com.carshop.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.carshop.domain.config.CarShopDomainConfig;

@Configuration
@ComponentScan(basePackages = "com.carshop.service")
@Import({ CarShopDomainConfig.class })
public class CarshopServiceConfig {

}
