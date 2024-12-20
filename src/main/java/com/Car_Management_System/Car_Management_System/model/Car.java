package com.Car_Management_System.Car_Management_System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;


/*
* This is the Car model class that will correspond to the Car table in database
* I have used spring-boot-starter-validation to validate the value
*/

@Entity
public class Car {
    /*
    * This is the unique identifier for each car
    * It will be used as primary key in database
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    // Here I have made name field mandatory and set max size to 50 character
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be less then 50 character")
    private String name;

    // Here I have made model field mandatory and set max size to 50 character
    @NotBlank(message = "Model name is required")
    @Size(max = 50, message = "Model name must be less then 50 character")
    private String model;

    // Here I have made Year field mandatory and set minimum value to 1900 and maximum value to 2024
    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year cannot be greater then 1900")
    @Max(value = 2024, message = "Year cannot precede present year")
    private int year;

    // Here I have made Price field to be greater then 0
    @Positive(message = "Price must be greater then 0")
    private double price;

    // Here I have made color field mandatory and set the size of it to be less then 20 character
    @NotBlank(message = "Color is required")
    @Size(max = 20, message = "Color name must be less then 20 character")
    private String color;

    // Here I have made fuel type field mandatory and set the size of it to be less then 20 character
    @NotBlank(message = "Fuel type is required")
    @Size(max = 20, message = "Fuel type must be less then 20 character")
    private String fuelType;

    public Car() {
    }

    public Car(Integer carId, String name, String model, int year, double price, String color, String fuelType) {
        this.carId = carId;
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.fuelType = fuelType;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
