package com.employeeManagement.employeeManagement.Service;

import com.employeeManagement.employeeManagement.EmployeeDto.DepartmentDto;
import com.employeeManagement.employeeManagement.EmployeeDto.EmployeeDto;
import com.employeeManagement.employeeManagement.Entity.DepartmentEntity;
import com.employeeManagement.employeeManagement.Entity.EmployeeEntity;
import com.employeeManagement.employeeManagement.Exceptions.EmployeeNotFound;
import com.employeeManagement.employeeManagement.Repository.DeptRepo;
import com.employeeManagement.employeeManagement.Repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DeptRepo deptRepo;
    ModelMapper modelMapper = new ModelMapper();

    //Method to save/create emp
    public EmployeeDto saveEmp(EmployeeDto employeeDto)
    {
        EmployeeEntity em = modelMapper.map(employeeDto, EmployeeEntity.class);
        EmployeeEntity result = employeeRepo.save(em);
        return modelMapper.map(result, EmployeeDto.class);
    }

    //To get all employee
    public List<EmployeeDto> getAllEmp()
    {
        List<EmployeeEntity> result = employeeRepo.findAll();
        return result
                .stream()
                .map(o -> modelMapper.map(o , EmployeeDto.class))
                .collect(Collectors.toList());
    }

    //Find a emp by his id
    public EmployeeDto findEmp(Long id)
    {
        EmployeeEntity emt = employeeRepo.findById(id).orElseThrow(
                        () -> new EmployeeNotFound("This employee "+id+" is not present")
        );
        System.out.println(emt.getDepartment());
        return modelMapper.map(emt, EmployeeDto.class);
    }

    //update using the put
    public EmployeeDto updateEmp(EmployeeDto employeeDto,Long id)
    {
            EmployeeEntity empEntity = employeeRepo.findById(id).orElseThrow(
                    () -> new EmployeeNotFound("This emp is not present")
            );
            empEntity.setFirstName(employeeDto.getFirstName());
            empEntity.setLastName(employeeDto.getLastName());
            empEntity.setEmail(employeeDto.getEmail());
            empEntity.setCity(employeeDto.getCity());
            empEntity.setPhoneNumber(employeeDto.getPhoneNumber());
            empEntity.setSalary(employeeDto.getSalary());
            empEntity.setPassword(empEntity.getPassword());

            EmployeeEntity result = employeeRepo.save(empEntity);
            return modelMapper.map(result, EmployeeDto.class);
    }

    //delete by id
    public void deleteEmp(Long id) {
        employeeRepo.deleteById(id);
    }

    public void saveEmpWithDept(EmployeeDto employeeDto)
    {
        Long departmentId = employeeDto.getDepartmentId();
        DepartmentEntity deptEty = deptRepo.findById(departmentId).orElse(null);

        EmployeeEntity eEntity = new EmployeeEntity();
        eEntity.setFirstName(employeeDto.getFirstName());
        eEntity.setLastName(employeeDto.getLastName());
        eEntity.setEmail(employeeDto.getEmail());
        eEntity.setCity(employeeDto.getCity());
        eEntity.setPhoneNumber(employeeDto.getPhoneNumber());
        eEntity.setSalary(employeeDto.getSalary());
        eEntity.setPassword(employeeDto.getPassword());
        //Assing Dept to the entity
        eEntity.setDepartment(deptEty);

        EmployeeEntity entity = employeeRepo.save(eEntity);
    }

//

    public List<EmployeeDto> bulkSaveEmp(List<EmployeeDto> employeeDtos) {

        List<EmployeeEntity> employeeEntities = employeeDtos
                .stream()
                .map(dto -> {

                    EmployeeEntity employee = new EmployeeEntity();

                    employee.setFirstName(dto.getFirstName());
                    employee.setLastName(dto.getLastName());
                    employee.setEmail(dto.getEmail());
                    employee.setPhoneNumber(dto.getPhoneNumber());
                    employee.setSalary(dto.getSalary());
                    employee.setPassword(dto.getPassword());
                    employee.setCity(dto.getCity());

                    // Find the existing department
                    DepartmentEntity department =
                            deptRepo.findById(dto.getDepartmentId())
                                    .orElseThrow(() ->
                                            new RuntimeException("Department not found"));

                    // Connect Employee -> Department
                    employee.setDepartment(department);

                    // Make sure this is treated as a new Employee
                    employee.setId(null);

                    return employee;
                })
                .collect(Collectors.toList());

        List<EmployeeEntity> savedEmployees =
                employeeRepo.saveAll(employeeEntities);

        return savedEmployees.stream()
                .map(employee -> {

                    EmployeeDto dto = new EmployeeDto();

                    dto.setFirstName(employee.getFirstName());
                    dto.setLastName(employee.getLastName());
                    dto.setEmail(employee.getEmail());
                    dto.setPhoneNumber(employee.getPhoneNumber());
                    dto.setSalary(employee.getSalary());
                    dto.setPassword(employee.getPassword());
                    dto.setCity(employee.getCity());

                    // Entity has DepartmentEntity
                    // DTO has departmentId
                    if (employee.getDepartment() != null) {
                        dto.setDepartmentId(
                                employee.getDepartment().getId()
                        );
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
