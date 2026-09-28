package com.example.quanlysinhvien.controller;

import com.example.quanlysinhvien.entity.Student;
import com.example.quanlysinhvien.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Yêu cầu 1: Thêm sinh viên
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student created = studentService.createStudent(student);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Yêu cầu 2: Xóa sinh viên

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

    // Yêu cầu 3: Tìm kiếm theo tên
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam(defaultValue = "") String name) {
        List<Student> results = studentService.searchByName(name);
        return ResponseEntity.ok(results);
    }

    // Yêu cầu 4: Lấy theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Yêu cầu 5: Lấy tất cả
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // Yêu cầu 6: Cập nhật sinh viên
    @PostMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable UUID id, @RequestBody Student studentDetails) {
        Student updated = studentService.updateStudent(id, studentDetails);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}