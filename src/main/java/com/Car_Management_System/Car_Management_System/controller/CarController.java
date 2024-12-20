package com.Car_Management_System.Car_Management_System.controller;

import com.Car_Management_System.Car_Management_System.model.Car;
import com.Car_Management_System.Car_Management_System.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* Controller class that expose endpoints.
* */

@RestController
@RequestMapping("/")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /*
    * Endpoint to add new car to the database
    * */
    @PostMapping("cars")
    public ResponseEntity<String> createCar(
            @RequestBody Car car
    ) {

        // Here I used try-catch block to implement error handling in case of error adding car
        // If error is encountered then user is notified with the error message and INTERNAL_SERVER_ERROR code

        try {
            Car savedCar = carService.addCar(car);
            return new ResponseEntity<>("Car added successfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
    * Endpoint to get all cars.
    * I have added request parameter as an optional argument, so that user can add parameter to filter by
    * name, model and year
    * */
    @GetMapping("cars")
    public ResponseEntity<Page<Car>> getAllCars(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String fuelType,
            Pageable pageable
    ) {
        // Here I have used try-catch block to get all car based on filter
        // If exception is occurring then it is handled by catch block and user is notified with error

        try {
            Page<Car> cars = carService.getAllCars(name, model, year, color, fuelType, pageable);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
