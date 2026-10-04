package com.employeeManagement.employeeManagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "Department")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<EmployeeEntity> employees;

}
