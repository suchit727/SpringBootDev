package com.example.mongopract.mapper;

import com.example.mongopract.entity.StudentEntity;
import com.example.mongopract.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentEntity modelToEntity(Student student);

    Student entityToModel(StudentEntity studentEntity);
}
