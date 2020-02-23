package com.carshop.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.carshop.dto.response.AppointmentDto;
import com.carshop.service.aggregator.AppointmentMediator;
import com.carshop.service.aggregator.CarMediator;
import com.carshop.service.validation.NotFoundException;

@Slf4j
@RequiredArgsConstructor
@Component
public class ServiceAuthEvaluator {

    private final AppointmentMediator appointmentMediator;

    public boolean canFind(UUID appointmentUuid) {
        return this.appointmentMediator.findByUuid(appointmentUuid)
                .isPresent();
    }
}
