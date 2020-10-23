package com.carshop.service.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Appointment;
import com.carshop.domain.repo.AppointmentRepository;
import com.carshop.service.event.message.AppointmentScheduledEvent;

@RequiredArgsConstructor
@Slf4j
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional(propagation = Propagation.MANDATORY)
    public Appointment create(Appointment entity) {
        log.info("Creating appointment for entity={}.", entity);
        Appointment appointment = this.appointmentRepository.save(entity);

        AppointmentScheduledEvent event = AppointmentScheduledEvent.builder()
                .source(this)
                .appointmentUuid(appointment.getUuid())
                .build();
        this.applicationEventPublisher.publishEvent(event);

        log.info("Created appointment={}.", appointment);
        return appointment;
    }

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public Optional<Appointment> findByUuid(UUID appointmentUuid) {
        return this.appointmentRepository.findByUuid(appointmentUuid);
    }

}
