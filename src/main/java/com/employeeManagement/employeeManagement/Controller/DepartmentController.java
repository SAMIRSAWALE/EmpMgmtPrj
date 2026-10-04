package com.employeeManagement.employeeManagement.Controller;

import com.employeeManagement.employeeManagement.EmployeeDto.DepartmentDto;
import com.employeeManagement.employeeManagement.Entity.DepartmentEntity;
import com.employeeManagement.employeeManagement.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService deptService;

    @PostMapping("/bSaveDept")
    public List<DepartmentDto> saveDepts(@RequestBody List<DepartmentDto> deptDtos)
    {
        return deptService.bulkSave(deptDtos);
    }
}
