package com.example.quanlysinhvien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.security.Security;

@SpringBootApplication
public class QuanlysinhvienApplication {

    public static void main(String[] args) {
        // Cho phép kết nối tới SQL Server phiên bản cũ (bỏ chặn TLS 1.0)
        Security.setProperty("jdk.tls.disabledAlgorithms", "SSLv3, RC4, DES, MD5withRSA");
        
        SpringApplication.run(QuanlysinhvienApplication.class, args);
    }
}