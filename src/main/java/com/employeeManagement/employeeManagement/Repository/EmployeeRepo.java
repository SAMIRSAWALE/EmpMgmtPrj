package com.employeeManagement.employeeManagement.Repository;

import com.employeeManagement.employeeManagement.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
}
