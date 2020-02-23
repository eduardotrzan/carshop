package com.carshop.service.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Service;
import com.carshop.dto.enums.ServiceStatusDto;
import com.carshop.dto.enums.ServiceTypeDto;
import com.carshop.dto.response.ServiceDto;

@Component
public class ServiceMapper {

    @Transactional(propagation = Propagation.MANDATORY)
    public List<ServiceDto> toDtos(List<Service> entities) {
        return Objects.requireNonNullElse(entities, Collections.<Service>emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(this::buildDto)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Optional<ServiceDto> toDto(Service entity) {
        if (entity == null) {
            return Optional.empty();
        }

        ServiceDto dto = buildDto(entity);
        return Optional.of(dto);
    }

    private ServiceDto buildDto(Service entity) {
        ServiceTypeDto type = entity.getType() == null
                ? null
                : ServiceTypeDto.valueOf(entity.getType().name());

        ServiceStatusDto status = entity.getStatus() == null
                ? null
                : ServiceStatusDto.valueOf(entity.getStatus().name());

        return ServiceDto.builder()
                .uuid(entity.getUuid())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .type(type)
                .status(status)
                .build();
    }
}
