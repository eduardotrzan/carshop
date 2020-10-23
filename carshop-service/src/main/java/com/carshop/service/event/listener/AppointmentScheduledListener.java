package com.carshop.service.event.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.carshop.dto.response.ServiceDto;
import com.carshop.service.aggregator.ServiceMediator;
import com.carshop.service.event.message.AppointmentScheduledEvent;

@Slf4j
@RequiredArgsConstructor
@Component
public class AppointmentScheduledListener {
/*
public class AppointmentScheduledListener implements ApplicationListener<AppointmentScheduledEvent> {

    private final ServiceMediator serviceMediator;

    @Override
    public void onApplicationEvent(AppointmentScheduledEvent event) {
        Objects.requireNonNull(event);

        log.info("Received event={}.", event);

        UUID appointmentUuid = event.getAppointmentUuid();
        ServiceDto service = this.serviceMediator.createDefaultServiceForAppointment(appointmentUuid);
        log.info("Created default service={} for appointmentUuid={}.", service, appointmentUuid);
    }
*/
    private final ServiceMediator serviceMediator;

    @EventListener
    public void createDefaultService(AppointmentScheduledEvent event) {
        Objects.requireNonNull(event);

        log.info("Received event={}.", event);

        UUID appointmentUuid = event.getAppointmentUuid();
        ServiceDto service = this.serviceMediator.createDefaultServiceForAppointment(appointmentUuid);
        log.info("Created default service={} for appointmentUuid={}.", service, appointmentUuid);
    }
}
