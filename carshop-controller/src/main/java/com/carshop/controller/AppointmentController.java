package com.carshop.controller;

import lombok.RequiredArgsConstructor;

import javax.annotation.security.PermitAll;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carshop.dto.request.AppointmentCreateDto;
import com.carshop.dto.response.AppointmentDto;
import com.carshop.service.aggregator.AppointmentMediator;
import com.carshop.service.validation.NotFoundException;

@RestController
@RequestMapping({ "/v1" })
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentMediator appointmentMediator;

    @PreAuthorize("@appointmentAuthEvaluator.canCreate(#carUuid)")
    @PostMapping(value = "/cars/{carUuid}/appointments", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto create(@PathVariable(value = "carUuid") UUID carUuid,
                                 @Validated @RequestBody AppointmentCreateDto request) {
        return this.appointmentMediator.create(carUuid, request);
    }

    @PermitAll
    @GetMapping(value = "/appointments/{appointmentUuid}", produces = "application/json")
    public AppointmentDto findComment(@PathVariable(value = "appointmentUuid") UUID appointmentUuid) {
        return this.appointmentMediator.findByUuid(appointmentUuid)
                .orElseThrow(() -> new NotFoundException(AppointmentDto.class.getSimpleName()));
    }
}
