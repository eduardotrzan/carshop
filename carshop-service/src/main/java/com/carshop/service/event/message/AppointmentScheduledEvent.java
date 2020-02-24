package com.carshop.service.event.message;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

@Getter
@ToString(of = { "appointmentUuid" })
public class AppointmentScheduledEvent extends ApplicationEvent {

    private UUID appointmentUuid;

    @Builder
    public AppointmentScheduledEvent(Object source, UUID appointmentUuid) {
        super(source);
        this.appointmentUuid = appointmentUuid;
    }
}
