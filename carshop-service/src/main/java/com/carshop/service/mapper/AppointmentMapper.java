package com.carshop.service.mapper;

import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Appointment;
import com.carshop.dto.request.AppointmentCreateDto;
import com.carshop.dto.response.AppointmentDto;
import com.carshop.dto.response.CarDto;
import com.carshop.dto.response.GarageDto;

@RequiredArgsConstructor
@Component
public class AppointmentMapper {

    private final CarMapper carMapper;
    private final GarageMapper garageMapper;

    @Transactional(propagation = Propagation.MANDATORY)
    public Optional<AppointmentDto> toDto(Appointment entity) {
        if (entity == null) {
            return Optional.empty();
        }

        AppointmentDto dto = buildDto(entity);
        return Optional.of(dto);
    }

    private AppointmentDto buildDto(Appointment entity) {
        CarDto car = this.carMapper.toDto(entity.getCar())
                .orElseThrow();
        GarageDto garage = this.garageMapper.toDto(entity.getGarage())
                .orElseThrow();

        return AppointmentDto.builder()
                .uuid(entity.getUuid())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .scheduleDate(entity.getScheduleDate())
                .car(car)
                .garage(garage)
                .build();
    }

    public Appointment toNewEntity(AppointmentCreateDto request) {
        Objects.requireNonNull(request);

        return Appointment.builder()
                .scheduleDate(request.getScheduleDate())
                .build();
    }

}
