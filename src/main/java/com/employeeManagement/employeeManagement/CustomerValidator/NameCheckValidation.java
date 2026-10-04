package com.employeeManagement.employeeManagement.CustomerValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameCheckValidation implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // let @NotBlank handle null
        return value.matches("^[A-Za-z ]+$");
    }
}