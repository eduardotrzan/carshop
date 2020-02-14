package com.carshop.service.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Appointment;
import com.carshop.domain.repo.AppointmentRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public Appointment create(Appointment entity) {
        return this.appointmentRepository.save(entity);
    }

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public Optional<Appointment> findByUuid(UUID appointmentUuid) {
        return this.appointmentRepository.findByUuid(appointmentUuid);
    }

}
