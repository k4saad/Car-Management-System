package com.Car_Management_System.Car_Management_System.service;

import com.Car_Management_System.Car_Management_System.model.Car;
import com.Car_Management_System.Car_Management_System.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
* Service class that contain business logic
* */

@Service
public class CarService {
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    private final CarRepository carRepository;

    // This method add car to the database using repository.
    // If error is encountered it will throw the error to the method that called this method.
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    // This method fetch all cars from database if all filter variable is empty
    public Page<Car> getAllCars(
            String name, String model, Integer year,
            String color, String fuelType,
            Pageable pageable
    ) {

        // Here I check if any filter variable is not null, if no filter is specified then all car is returned
        if (name != null) {
            return carRepository.findByNameContainingIgnoreCase(name, pageable);
        }
        if (model != null) {
            return carRepository.findByModelContainingIgnoreCase(model, pageable);
        }
        if (year != null) {
            return carRepository.findByYear(year, pageable);
        }
        if (color != null) {
            return carRepository.findByColorContainingIgnoreCase(color, pageable);
        }
        if (fuelType != null) {
            return carRepository.findByFuelTypeContainingIgnoreCase(fuelType, pageable);
        }

        return carRepository.findAll(pageable);
    }
}
