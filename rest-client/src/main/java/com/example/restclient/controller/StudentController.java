package com.example.restclient.controller;

import com.example.restclient.dto.StudentDto;
import com.example.restclient.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/api/health-check")
    public String healthCheck() {
        return "ok, fine, running";
    }

    @GetMapping("/api/student/{studentId}")
    public StudentDto getStudent(@PathVariable String studentId) {
        return studentService.getStudent(studentId);
    }


}
