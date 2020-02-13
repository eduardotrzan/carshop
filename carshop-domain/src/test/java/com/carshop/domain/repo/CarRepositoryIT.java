package com.carshop.domain.repo;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.carshop.domain.entity.Car;
import com.carshop.domain.testutils.AbstractCarshopDomainRepoIT;

@Slf4j
public class CarRepositoryIT extends AbstractCarshopDomainRepoIT {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void saveCar() {
        Car car = super.getCarshopDatabasePrefillUtils()
                .withRandomCar()
                .saveAndGet()
                .getCar();
        log.info("car={}", car);

        assertThat(car).isNotNull();
        assertThat(car.getId())
                .isNotNull()
                .isPositive();
        assertThat(car.getMake())
                .isNotBlank();
        assertThat(car.getModel())
                .isNotBlank();
        assertThat(car.getYear())
                .isNotNull()
                .isPositive();
    }
}
