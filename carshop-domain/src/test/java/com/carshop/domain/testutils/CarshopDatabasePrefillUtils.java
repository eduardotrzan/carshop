package com.carshop.domain.testutils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carshop.domain.entity.Appointment;
import com.carshop.domain.entity.Car;
import com.carshop.domain.entity.Garage;
import com.carshop.domain.entity.enums.GarageStatus;
import com.carshop.domain.repo.AppointmentRepository;
import com.carshop.domain.repo.CarRepository;
import com.carshop.domain.repo.GarageRepository;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.DateProducer;
import com.devskiller.jfairy.producer.text.TextProducer;

@Slf4j
@RequiredArgsConstructor
@Component
public class CarshopDatabasePrefillUtils {

    private final CarRepository carRepository;

    private final GarageRepository garageRepository;

    private final AppointmentRepository appointmentRepository;

    private Car car;

    private Garage garage;

    private Appointment appointment;

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DatabasePrefillContext {

        private Car car;

        private Garage garage;

        private Appointment appointment;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DatabasePrefillContext saveAndGet() {
        doSave();
        DatabasePrefillContext databasePrefillContext = DatabasePrefillContext.builder()
                .car(this.car)
                .garage(this.garage)
                .appointment(this.appointment)
                .build();
        flush();
        return databasePrefillContext;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save() {
        doSave();
        flush();
    }

    private void doSave() {
        this.saveCar();
        this.saveGarage();
        this.saveAppointment();
    }

    private void flush() {
        this.car = null;
        this.garage = null;
        this.appointment = null;
    }

    private void saveCar() {
        log.info("Saving Car...");
        if (this.car == null) {
            log.info("Car is null, skipping save.");
            return;
        }

        this.car = this.carRepository.save(this.car);
        log.info("Saved car={}.", this.car);
    }

    private void saveGarage() {
        log.info("Saving Garage...");
        if (this.garage == null) {
            log.info("Garage is null, skipping save.");
            return;
        }
        this.garage = this.garageRepository.save(this.garage);
        log.info("Saved garage={}.", this.garage);
    }

    private void saveAppointment() {
        log.info("Saving Appointment...");
        if (this.appointment == null) {
            log.info("Appointment is null, skipping save.");
            return;
        }

        if (this.car == null) {
            log.info("Car is null, skipping save.");
            return;
        }

        if (this.garage == null) {
            log.info("Garage is null, skipping save.");
            return;
        }

        this.appointment.setCar(this.car);
        this.appointment.setGarage(this.garage);

        if (this.garage.getAppointments() == null || this.garage.getAppointments().isEmpty()) {
            this.garage.setAppointments(new ArrayList<>());
        }
        this.garage.getAppointments().add(this.appointment);

        this.appointment = this.appointmentRepository.save(this.appointment);
        log.info("Saved appointment={}.", this.appointment);
    }

    public CarshopDatabasePrefillUtils withRandomCar() {
        TextProducer textProducer = Fairy.create().textProducer();
        this.car = Car.builder()
                .make(textProducer.latinSentence())
                .model(textProducer.latinSentence())
                .year(this.getRandomNumberInRange(1950, 2020))
                .build();
        return this;
    }

    public CarshopDatabasePrefillUtils withRandomGarage() {
        TextProducer textProducer = Fairy.create().textProducer();
        this.garage = Garage.builder()
                .door(this.getRandomNumberInRange(1, 10))
                .status(GarageStatus.FREE)
                .build();
        return this;
    }

    public CarshopDatabasePrefillUtils withRandomAppointment() {
        DateProducer dateProducer = Fairy.create().dateProducer();
        this.appointment = Appointment.builder()
                .scheduleDate(dateProducer.randomDateInTheFuture().atOffset(ZoneOffset.MAX))
                .build();
        return this;
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        int bound = (max - min) + 1;
        return new Random().nextInt(bound) + min;
    }
}
