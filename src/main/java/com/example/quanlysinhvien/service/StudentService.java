package com.example.quanlysinhvien.service;

import com.example.quanlysinhvien.entity.Student;
import com.example.quanlysinhvien.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
    if (student.getId() == null) {
        student.setId(UUID.randomUUID());
    }
    return studentRepository.save(student);
}

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String name) {
        return studentRepository.findByFullNameContainingIgnoreCase(name);
    }

    public boolean deleteStudent(UUID id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Student updateStudent(UUID id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setStudentCode(studentDetails.getStudentCode());
            student.setFullName(studentDetails.getFullName());
            student.setEmail(studentDetails.getEmail());
            student.setPhone(studentDetails.getPhone());
            student.setClassName(studentDetails.getClassName());
            return studentRepository.save(student);
        }).orElse(null);
    }
}