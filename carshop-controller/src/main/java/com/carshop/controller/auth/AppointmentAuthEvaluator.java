package com.carshop.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.carshop.service.aggregator.CarMediator;

@Slf4j
@RequiredArgsConstructor
@Component
public class AppointmentAuthEvaluator {

    private final CarMediator carMediator;

    public boolean canCreate(UUID carUuid) {
        return this.carMediator.findByUuid(carUuid)
                .isPresent();
    }

    public boolean canFind(UUID appointmentUuid) {
        return true;
    }
}
