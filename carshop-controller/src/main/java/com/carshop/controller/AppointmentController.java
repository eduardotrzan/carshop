package com.carshop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(value = "Appointment Management")
@RestController
@RequestMapping({ "/v1" })
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentMediator appointmentMediator;

    @ApiOperation(value = "Creates an Appointment for a Car base on uuid.", response = AppointmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully create an Appointment"),
            @ApiResponse(code = 401, message = "You are not authorized to create an Appointment.")
    })
    @PreAuthorize("@appointmentAuthEvaluator.canCreate(#carUuid)")
    @PostMapping(value = "/cars/{carUuid}/appointments", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto create(@PathVariable(value = "carUuid") UUID carUuid,
                                 @Validated @RequestBody AppointmentCreateDto request) {
        return this.appointmentMediator.create(carUuid, request);
    }

    @ApiOperation(value = "Retrieves an Appointment by its UUID.", response = AppointmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieves an Appointment."),
            @ApiResponse(code = 401, message = "You are not authorized to view the Appointment."),
            @ApiResponse(code = 404, message = "The Appointment you were trying to reach is NOT FOUND.")
    })
    @PermitAll
    @PreAuthorize("@appointmentAuthEvaluator.canFind(#appointmentUuid)")
    @GetMapping(value = "/appointments/{appointmentUuid}", produces = "application/json")
    public AppointmentDto findComment(@PathVariable(value = "appointmentUuid") UUID appointmentUuid) {
        return this.appointmentMediator.findByUuid(appointmentUuid)
                .orElseThrow(() -> new NotFoundException(AppointmentDto.class.getSimpleName()));
    }
}
