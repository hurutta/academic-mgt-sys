package com.example.restclient.service;

import com.example.restclient.dto.StudentDto;

public interface StudentService {

    public StudentDto registerStudent(StudentDto studentDto);
    public StudentDto getStudent(String studentId);
    public StudentDto deleteStudent(String studentId);
}
