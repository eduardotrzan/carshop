package com.carshop.domain.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carshop.domain.entity.Garage;
import com.carshop.domain.entity.enums.GarageStatus;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    Optional<Garage> findByUuid(UUID garageUuid);

    List<Garage> findAllByStatus(GarageStatus status);
}
