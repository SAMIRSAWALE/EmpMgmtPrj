package com.employeeManagement.employeeManagement.EmployeeDto;

import com.employeeManagement.employeeManagement.CustomerValidator.ValidName;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeDto {

    @NotBlank(message = "Name should not be empty")
    @ValidName(message = "Name should be valid")
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @Digits(integer = 10, fraction = 2)
    private String phoneNumber;

    @Positive
    private BigDecimal salary;

    @NotBlank
    private String city;

    private Long departmentId;

    private String password;

    private DepartmentDto department;
}
