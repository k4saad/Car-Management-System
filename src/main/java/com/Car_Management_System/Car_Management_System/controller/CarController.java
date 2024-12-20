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
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /*
    * Endpoint to add new car to the database
    * */
    @PostMapping
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
    @GetMapping
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


    /*
    *
    * Endpoint to Globally search for cars by name, model, year, color, or fuel type
    */
    @GetMapping("/search")
    public ResponseEntity<Page<Car>> searchCars(
            @RequestParam String keyword,
            Pageable pageable
    ) {
        try {
            Page<Car> cars = carService.globalSearch(keyword, pageable);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
    * Endpoint for updating existing car using id
    * */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCar(
            @PathVariable Integer id,
            @RequestBody Car updatedCar
    ) {
        try {
            Car car = carService.updateCar(id, updatedCar);
            return new ResponseEntity<>("Car updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    * Endpoint for deleting the car using id
    * */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(
            @PathVariable Integer id
    ) {
        try {
            carService.deleteCar(id);
            return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete car: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
