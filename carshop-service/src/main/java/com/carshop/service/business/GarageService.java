package com.carshop.service.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Garage;
import com.carshop.domain.repo.GarageRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class GarageService {

    private final GarageRepository garageRepository;

    @Transactional(propagation = Propagation.MANDATORY)
    public Garage create(Garage entity) {
        return this.garageRepository.save(entity);
    }

    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public Optional<Garage> findByUuid(UUID garageUuid) {
        return this.garageRepository.findByUuid(garageUuid);
    }

}
