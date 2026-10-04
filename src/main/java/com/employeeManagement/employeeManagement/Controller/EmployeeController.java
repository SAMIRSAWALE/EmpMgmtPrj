package com.employeeManagement.employeeManagement.Controller;

import com.employeeManagement.employeeManagement.EmployeeDto.EmployeeDto;
import com.employeeManagement.employeeManagement.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public String saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto edt = employeeService.saveEmp(employeeDto);
        return "Created";
    }

    @GetMapping("/getAllEmps")
    public List<EmployeeDto> getAllEmps() {
        return employeeService.getAllEmp();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmpById(@PathVariable Long id) {
        EmployeeDto edt =  employeeService.findEmp(id);
        return ResponseEntity.ok(edt);
    }

    @PutMapping("/update/{id}")
    public EmployeeDto updateEmp(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmp(employeeDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmp(@PathVariable Long id) {
            employeeService.deleteEmp(id);
            return "Deleted";
    }

    @GetMapping("/Sout")
    public void juspreet(@RequestBody EmployeeDto employeeDto) {
            employeeService.saveEmpWithDept(employeeDto);
    }

    @PostMapping("/bulkSave")
    public List<EmployeeDto> bulkSaveEmp(@RequestBody List<EmployeeDto> employeeDto) {
        return  employeeService.bulkSaveEmp(employeeDto);
    }
}