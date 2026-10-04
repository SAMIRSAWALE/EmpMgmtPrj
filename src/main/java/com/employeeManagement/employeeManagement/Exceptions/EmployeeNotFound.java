package com.employeeManagement.employeeManagement.Exceptions;

public class EmployeeNotFound extends RuntimeException{
    public EmployeeNotFound(String msg)
    {
        super(msg);
    }
}
