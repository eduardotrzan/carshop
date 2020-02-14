package com.carshop.service.aggregator;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Car;
import com.carshop.dto.request.CarCreateDto;
import com.carshop.dto.response.CarDto;
import com.carshop.service.business.CarService;
import com.carshop.service.mapper.CarMapper;
import com.carshop.service.validation.NotFoundException;

@RequiredArgsConstructor
@Component
public class CarMediator {

    private final CarService carService;

    private final CarMapper carMapper;

    @Transactional
    public CarDto create(CarCreateDto request) {
        Car entity = this.carMapper.toNewEntity(request);
        Car savedEntity = this.carService.create(entity);

        return this.carMapper
                .toDto(savedEntity)
                .orElseThrow(() -> new NotFoundException(Car.class.getSimpleName()));
    }

    @Transactional(readOnly = true)
    public Optional<CarDto> findByUuid(UUID carUuid) {
        return this.carService
                .findByUuid(carUuid)
                .flatMap(carMapper::toDto);
    }

}
