package com.Car_Management_System.Car_Management_System.repository;

import com.Car_Management_System.Car_Management_System.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
* This is the repository interface for managing car models.
* This will provide a way to interact with the database.
* */

public interface CarRepository extends JpaRepository<Car, Integer> {

    /*
     * I made a Derived Query Method to filter Car model by name, model, year, color and fuel type
     * */

    Page<Car> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Car> findByModelContainingIgnoreCase(String model, Pageable pageable);
    Page<Car> findByYear(Integer year, Pageable pageable);
    Page<Car> findByColorContainingIgnoreCase(String color, Pageable pageable);
    Page<Car> findByFuelTypeContainingIgnoreCase(String fuelType, Pageable pageable);

    /*
    * I made a custom query to search Car model by name, model, year, color and fuel type
    * */

    @Query("SELECT c FROM Car c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.model) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(c.year AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.color) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.fuelType) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Car> globalSearch(String keyword, Pageable pageable);
}
