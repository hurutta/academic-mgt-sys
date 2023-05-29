package com.example.restclient.controller;

import com.example.restclient.dto.StudentDto;
import com.example.restclient.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/api/health-check")
    public String healthCheck() {
        return "ok, fine, running";
    }

    @PostMapping("/api/student/register")
    public StudentDto registerStudent(@RequestBody StudentDto studentDto) {
        return studentService.registerStudent(studentDto);
    }

    @GetMapping("/api/student/{studentId}")
    public StudentDto getStudent(@PathVariable String studentId) {
        return studentService.getStudent(studentId);
    }

    @DeleteMapping("/api/student/{studentId}")
    public StudentDto deleteStudent(@PathVariable String studentId) {
        return studentService.deleteStudent(studentId);
    }


}
