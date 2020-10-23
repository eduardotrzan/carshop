package com.carshop.service.aggregator;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Appointment;
import com.carshop.domain.entity.Service;
import com.carshop.dto.response.ServiceDto;
import com.carshop.service.business.AppointmentService;
import com.carshop.service.business.ServiceService;
import com.carshop.service.mapper.ServiceMapper;
import com.carshop.service.validation.NotFoundException;

@RequiredArgsConstructor
@Component
public class ServiceMediator {

    private final ServiceService serviceService;
    private final AppointmentService appointmentService;

    private final ServiceMapper serviceMapper;

    @Transactional(readOnly = true)
    public List<ServiceDto> findPendingServicesForAppointmentUuid(UUID appointmentUuid) {
        List<Service> services = this.serviceService.findPendingServicesForAppointmentUuid(appointmentUuid);
        return this.serviceMapper.toDtos(services);
    }

    @Transactional
    public ServiceDto createDefaultServiceForAppointment(UUID appointmentUuid) {
        Appointment appointment = this.appointmentService.findByUuid(appointmentUuid)
                .orElseThrow(() -> new NotFoundException(Appointment.class.getSimpleName()));

        Service savedEntity = this.serviceService.createDefaultService(appointment);

        return this.serviceMapper
                .toDto(savedEntity)
                .orElseThrow(() -> new NotFoundException(Service.class.getSimpleName()));
    }
}
