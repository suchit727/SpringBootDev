package com.example.manytomany.service;

import com.example.manytomany.entity.DepartmentEntity;
import com.example.manytomany.mapper.DepartmentMapper;
import com.example.manytomany.model.Department;
import com.example.manytomany.model.DepartmentResponse;
import com.example.manytomany.repository.DepartmentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentResponse createEmployee(Department department) {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        DepartmentEntity departmentEntity = departmentMapper.modeldToEntityd(department);
        departmentRepository.save(departmentEntity);
        log.info("Records inserted in department successfully");
        departmentResponse.setId(departmentEntity.getId());
        return departmentResponse;
    }
}
