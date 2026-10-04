package com.employeeManagement.employeeManagement.Exceptions;

import com.employeeManagement.employeeManagement.EmployeeDto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFound enf)
   {
       return ResponseEntity
               .status(HttpStatus.NOT_FOUND)
               .body(enf.getMessage());
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex)
    {
        Map<String, String> errors = new HashMap<>();
        System.out.println("this si teh impo log"+ex.getBindingResult().toString());
        System.out.println("this si teh impo log"+ex.getBindingResult().getFieldErrors().toString());
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }



}
