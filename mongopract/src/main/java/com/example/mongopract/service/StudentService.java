package com.example.mongopract.service;

import com.example.mongopract.entity.StudentEntity;
import com.example.mongopract.mapper.StudentMapper;
import com.example.mongopract.model.Student;
import com.example.mongopract.model.StudentResponse;
import com.example.mongopract.repository.StudentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public StudentResponse createStudent(Student student) {
        StudentEntity studentEntity;
        studentEntity = studentMapper.modelToEntity(student);
        studentRepository.save(studentEntity);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());

        log.info("Student  {}  created Successfully", student.getName());
        return studentResponse;
    }

    public Student getStudent(Long id) {
        Student student = new Student();
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if (studentEntity.isPresent()) {
            student = studentMapper.entityToModel(studentEntity.get());
        }
        log.info("Student With id {} retrieved successfully", id);
        return student;
    }

    public void updateStudent(Long id, Student student) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        if (optionalStudentEntity.isPresent()) {
            StudentEntity studentEntity = optionalStudentEntity.get();
            studentEntity = studentMapper.modelToEntity(student);
             /*studentEntity.setId(student.getId());
             studentEntity.setName(student.getName());
             studentEntity.setCity(student.getCity());
             studentEntity.setAge(student.getAge());
*/
            studentRepository.save(studentEntity);
            log.info("Student with id {} updated successfully", id);
        }

    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        log.info("Student with id {} deleted Successfully", id);
    }
}
