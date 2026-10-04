package com.employeeManagement.employeeManagement.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
//@Table(name = "Employees")
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "salary", precision = 12, scale = 2)
    private BigDecimal salary;

    private String password;
    private String city;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
}
