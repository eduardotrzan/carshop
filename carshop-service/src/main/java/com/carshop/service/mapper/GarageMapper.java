package com.carshop.service.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Garage;
import com.carshop.dto.enums.GarageStatusDto;
import com.carshop.dto.response.GarageDto;

@Component
public class GarageMapper {

    @Transactional(propagation = Propagation.MANDATORY)
    public Optional<GarageDto> toDto(Garage entity) {
        if (entity == null) {
            return Optional.empty();
        }

        GarageDto dto = buildDto(entity);
        return Optional.of(dto);
    }

    private GarageDto buildDto(Garage entity) {
        GarageStatusDto status = GarageStatusDto.valueOf(entity.getStatus().name());
        return GarageDto.builder()
                .uuid(entity.getUuid())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .status(status)
                .door(entity.getDoor())
                .build();
    }

}
