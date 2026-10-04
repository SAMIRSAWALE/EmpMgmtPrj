package com.employeeManagement.employeeManagement.Repository;

import com.employeeManagement.employeeManagement.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepo extends JpaRepository<DepartmentEntity, Long> {

}
