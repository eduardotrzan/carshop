package com.carshop.controller;

import lombok.RequiredArgsConstructor;

import javax.annotation.security.PermitAll;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carshop.dto.request.CarCreateDto;
import com.carshop.dto.response.CarDto;
import com.carshop.service.aggregator.CarMediator;

@RestController
@RequestMapping({ "/v1/cars" })
@RequiredArgsConstructor
public class CarController {

    private final CarMediator carMediator;

    @PermitAll
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto create(@Validated @RequestBody CarCreateDto request) {
        return this.carMediator.create(request);
    }

}
