package com.carshop.service.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Appointment;
import com.carshop.domain.entity.enums.ServiceStatus;
import com.carshop.domain.entity.enums.ServiceType;
import com.carshop.domain.repo.ServiceRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public com.carshop.domain.entity.Service create(com.carshop.domain.entity.Service entity) {
        return this.serviceRepository.save(entity);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public com.carshop.domain.entity.Service createDefaultService(Appointment appointment) {
        com.carshop.domain.entity.Service entity = com.carshop.domain.entity.Service.builder()
                .appointment(appointment)
                .status(ServiceStatus.PENDING)
                .type(ServiceType.CHECK_TIRES)
                .build();

        // This is optional, in case the currently object requires to have the added service,
        // this won't impact hibernate from properly save it.
        List<com.carshop.domain.entity.Service> services = appointment.getServices();
        if (services == null) {
            appointment.setServices(new ArrayList<>());
            services = appointment.getServices();
        }
        services.add(entity);
        // end optional

        return this.serviceRepository.save(entity);
    }

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public List<com.carshop.domain.entity.Service> findPendingServicesForAppointmentUuid(UUID appointmentUuid) {
        return this.serviceRepository.findPendingServicesForAppointmentUuid(appointmentUuid);
    }
}
