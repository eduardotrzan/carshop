package com.carshop.service.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.repo.ServiceRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public List<com.carshop.domain.entity.Service> findPendingServicesForAppointmentUuid(UUID appointmentUuid) {
        return this.serviceRepository.findPendingServicesForAppointmentUuid(appointmentUuid);
    }

}
