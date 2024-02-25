package com.rently.rentlyAPI.validators;


import com.rently.rentlyAPI.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {

    private final ValidatorFactory factor = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factor.getValidator();

    public void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            Set<String> errorMessages = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toSet());
            //raise exception
            throw new ObjectValidationException(errorMessages, object.getClass().getName());
        }
    }
}
