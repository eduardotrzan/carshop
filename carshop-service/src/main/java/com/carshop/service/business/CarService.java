package com.carshop.service.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Car;
import com.carshop.domain.repo.CarRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class CarService {

    private final CarRepository carRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public Car create(Car entity) {
        return this.carRepository.save(entity);
    }

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public Optional<Car> findByUuid(UUID carUuid) {
        return this.carRepository.findByUuid(carUuid);
    }

}
