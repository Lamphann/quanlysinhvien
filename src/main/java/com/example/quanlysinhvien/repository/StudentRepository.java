package com.example.quanlysinhvien.repository;

import com.example.quanlysinhvien.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByFullNameContainingIgnoreCase(String fullName);
}