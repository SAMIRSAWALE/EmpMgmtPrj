package com.employeeManagement.employeeManagement.Service;

import com.employeeManagement.employeeManagement.EmployeeDto.DepartmentDto;
import com.employeeManagement.employeeManagement.EmployeeDto.EmployeeDto;
import com.employeeManagement.employeeManagement.Entity.DepartmentEntity;
import com.employeeManagement.employeeManagement.Repository.DeptRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DeptRepo deptRepo;

    ModelMapper modelMapper = new ModelMapper();

    public DepartmentDto save(DepartmentDto deptDto) {
        DepartmentEntity de = modelMapper.map(deptDto, DepartmentEntity.class);
        DepartmentEntity deptEntity = deptRepo.save(de);
        return modelMapper.map(deptEntity, DepartmentDto.class);
    }

    public  List<DepartmentDto> bulkSave(List<DepartmentDto> deptDtos) {
            List<DepartmentEntity> deptEntities = deptDtos.stream()
                                                          .map(o -> modelMapper.map(o , DepartmentEntity.class)).collect(Collectors.toList());
            List<DepartmentEntity> edt =  deptRepo.saveAll(deptEntities);
            return edt.stream().map(o -> modelMapper.map(o, DepartmentDto.class)).collect(Collectors.toList());
    }

}
