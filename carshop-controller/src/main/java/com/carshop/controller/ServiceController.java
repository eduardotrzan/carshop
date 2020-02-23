package com.carshop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carshop.dto.response.ServiceDto;
import com.carshop.service.aggregator.ServiceMediator;

@Api(value = "Service Management")
@RestController
@RequestMapping({ "/v1" })
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceMediator serviceMediator;

    @ApiOperation(value = "Retrieves a Service by its UUID.", response = ServiceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieves a Service."),
            @ApiResponse(code = 401, message = "You are not authorized to view the Service.")
    })
    @PreAuthorize("@serviceAuthEvaluator.canFind(#appointmentUuid)")
    @GetMapping(value = "/appointments/{appointmentUuid}/services", produces = "application/json")
    public List<ServiceDto> findPendingServicesForAppointmentUuid(@PathVariable(value = "appointmentUuid") UUID appointmentUuid) {
        return this.serviceMediator.findPendingServicesForAppointmentUuid(appointmentUuid);
    }

}
