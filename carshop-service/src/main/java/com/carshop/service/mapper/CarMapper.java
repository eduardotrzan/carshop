package com.carshop.service.mapper;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Car;
import com.carshop.dto.request.CarCreateDto;
import com.carshop.dto.response.CarDto;

@Component
public class CarMapper {

    @Transactional(propagation = Propagation.MANDATORY)
    public Optional<CarDto> toDto(Car entity) {
        if (entity == null) {
            return Optional.empty();
        }

        CarDto dto = buildDto(entity);
        return Optional.of(dto);
    }

    private CarDto buildDto(Car entity) {
        return CarDto.builder()
                .uuid(entity.getUuid())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .make(entity.getMake())
                .model(entity.getModel())
                .year(entity.getYear())
                .build();
    }

    public Car toNewEntity(CarCreateDto request) {
        Objects.requireNonNull(request);

        return Car.builder()
                .make(request.getMake())
                .model(request.getModel())
                .year(request.getYear())
                .build();
    }

}
