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

    // This method will take the keyword and then call the repository method to search the database with specified keyword
    public Page<Car> globalSearch(String keyword, Pageable pageable) {
        return carRepository.globalSearch(keyword, pageable);
    }

    public Car updateCar(Integer id, Car updatedCar) {

        // First we find if the car exist or not, if not then we will throw the Run time Exception
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + id));

        // Now we will update field with the values we received from the user
        existingCar.setName(updatedCar.getName());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setYear(updatedCar.getYear());
        existingCar.setPrice(updatedCar.getPrice());
        existingCar.setColor(updatedCar.getColor());
        existingCar.setFuelType(updatedCar.getFuelType());

        // Now we will save the updated car details and return the value we get from the save method
        return carRepository.save(existingCar);
    }

    public void deleteCar(Integer id) {

        // First we will check if the car with given id exists of not
        // If it does not exist then we will throw run time exception.
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found with ID: " + id);
        }

        // If exist then we will delete the car with given id
        carRepository.deleteById(id);
    }
}
