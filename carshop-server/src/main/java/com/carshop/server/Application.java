package com.carshop.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import com.carshop.controller.config.CarshopControllerConfig;
import com.carshop.server.config.ApplicationConfig;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({
                                       ApplicationConfig.class
                               })
@Import({
//                SwaggerConfig.class,

                // Controller Modules
                CarshopControllerConfig.class

        })
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ApplicationConfig config;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    private void started() {
        TimeZone.setDefault(TimeZone.getTimeZone(this.config.getTimeZone()));
    }

    @Override
    public void run(String... strings) {
        log.info("Running system with config={}.", this.config);
    }
}
