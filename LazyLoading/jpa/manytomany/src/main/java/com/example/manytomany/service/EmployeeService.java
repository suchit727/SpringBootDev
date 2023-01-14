package com.example.manytomany.service;

import com.example.manytomany.entity.DepartmentEntity;
import com.example.manytomany.entity.EmployeeEntity;
import com.example.manytomany.mapper.DepartmentMapper;
import com.example.manytomany.mapper.EmployeeMapper;
import com.example.manytomany.model.Department;
import com.example.manytomany.model.Employee;
import com.example.manytomany.model.EmployeeResponse;
import com.example.manytomany.repository.DepartmentRepository;
import com.example.manytomany.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    public EmployeeResponse createEmployee(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        EmployeeEntity employeeEntity = employeeMapper.modelToEntity(employee);
        employeeRepository.save(employeeEntity);
        log.info("employee created successfully!");
        employeeResponse.setId(employeeEntity.getId());
        return employeeResponse;
    }


    public Employee getEmployeeById(Long id) {

        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        log.info("This is line 1");
        Employee employee = new Employee();
        if (optionalEmployeeEntity.isPresent()) {
            EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
            employee = employeeMapper.entityToModel(employeeEntity);
            log.info("Code after line 2");

            log.info("employee found with id {} ", id);
        } else {
            log.info("employee id {} ", id + "not found");
        }
        return employee;
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
        log.info("employee id {} ", id + "deleted successfully");
    }

    public void updateEmployeeById(Long id, Employee employee) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        if (optionalEmployeeEntity.isPresent()) {
            optionalEmployeeEntity.get().setFirstName(employee.getFirstName());
            optionalEmployeeEntity.get().setLastName(employee.getLastName());
            employeeRepository.save(optionalEmployeeEntity.get());
            log.info("employee updated with id {} ", id);
        } else {
            log.info("employee id {} ", id + "not found");
        }
    }

    public void updateDepartmentById(Long id, Department department) {
        Optional<DepartmentEntity> optionalDepartmentEntity = departmentRepository.findById(id);
        if (optionalDepartmentEntity.isPresent()) {
            optionalDepartmentEntity.get().setName(department.getName());
            departmentRepository.save(optionalDepartmentEntity.get());
            log.info("department updated with id {} ", id);
        } else {
            log.info("department id {} ", id + "not found");

        }
    }

    public void updateEmployee(Long empId, Long id) {
        Set<DepartmentEntity> departmentEntitySet = null;
        EmployeeEntity employeeEntity=employeeRepository.findById(empId).get();
        DepartmentEntity departmentEntity=departmentRepository.findById(id).get();
        departmentEntitySet=employeeEntity.getDepartment();
        departmentEntitySet.remove(departmentEntity);
        departmentEntitySet.add(departmentEntity);
        employeeEntity.setDepartment(departmentEntitySet);
        employeeRepository.save(employeeEntity);
    }
}
