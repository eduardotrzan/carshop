package com.carshop.service.aggregator;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Service;
import com.carshop.dto.response.ServiceDto;
import com.carshop.service.business.ServiceService;
import com.carshop.service.mapper.ServiceMapper;

@RequiredArgsConstructor
@Component
public class ServiceMediator {

    private final ServiceService serviceService;

    private final ServiceMapper serviceMapper;

    @Transactional(readOnly = true)
    public List<ServiceDto> findPendingServicesForAppointmentUuid(UUID appointmentUuid) {
        List<Service> services = this.serviceService.findPendingServicesForAppointmentUuid(appointmentUuid);
        return this.serviceMapper.toDtos(services);
    }

}
