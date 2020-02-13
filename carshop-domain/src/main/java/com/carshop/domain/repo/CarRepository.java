package com.carshop.domain.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carshop.domain.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByUuid(UUID carUuid);

}
