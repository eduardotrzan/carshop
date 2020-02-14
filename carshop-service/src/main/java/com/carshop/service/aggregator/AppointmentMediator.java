package com.carshop.service.aggregator;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Appointment;
import com.carshop.domain.entity.Car;
import com.carshop.domain.entity.Garage;
import com.carshop.domain.entity.enums.GarageStatus;
import com.carshop.dto.request.AppointmentCreateDto;
import com.carshop.dto.response.AppointmentDto;
import com.carshop.service.business.AppointmentService;
import com.carshop.service.business.CarService;
import com.carshop.service.business.GarageService;
import com.carshop.service.mapper.AppointmentMapper;
import com.carshop.service.validation.InternalServerException;
import com.carshop.service.validation.NotFoundException;

@RequiredArgsConstructor
@Component
public class AppointmentMediator {

    private final CarService carService;
    private final GarageService garageService;
    private final AppointmentService appointmentService;

    private final AppointmentMapper appointmentMapper;

    @Transactional
    public AppointmentDto create(UUID carUuid, AppointmentCreateDto request) {
        Car car = this.carService.findByUuid(carUuid)
                .orElseThrow(() -> new NotFoundException(Car.class.getSimpleName()));
        Garage garage = this.garageService.findAllByStatus(GarageStatus.FREE)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(Garage.class.getSimpleName()));

        Appointment entity = this.appointmentMapper.toNewEntity(request);
        entity.setCar(car);
        entity.setGarage(garage);

        Appointment savedEntity = this.appointmentService.create(entity);

        return this.appointmentMapper
                .toDto(savedEntity)
                .orElseThrow(() -> new InternalServerException("Mapper error"));
    }

    @Transactional(readOnly = true)
    public Optional<AppointmentDto> findByUuid(UUID appointmentUuid) {
        return this.appointmentService
                .findByUuid(appointmentUuid)
                .flatMap(appointmentMapper::toDto);
    }

}
