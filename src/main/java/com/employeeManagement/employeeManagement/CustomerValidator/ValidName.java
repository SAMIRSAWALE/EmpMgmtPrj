package com.employeeManagement.employeeManagement.CustomerValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = NameCheckValidation.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "Invalid Name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
