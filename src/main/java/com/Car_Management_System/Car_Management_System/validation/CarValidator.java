package com.Car_Management_System.Car_Management_System.validation;

import com.Car_Management_System.Car_Management_System.model.Car;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * This is a custom validation component for validating the fields of the Car model
 **/

@Component
public class CarValidator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();


    /*
    * Here in this method we validate the Car object and if there are any
    * Validation error then that error message is returned.
    * Otherwise an empty set is returned
    * */
    public Set<String> validate(Car car){
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        if(!violations.isEmpty()){
            return violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
