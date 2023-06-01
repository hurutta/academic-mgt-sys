package com.example.restclient.service;

import com.example.restclient.dto.AnalysisDto;
import com.example.restclient.dto.StudentDto;

import java.util.List;

public interface StudentService {

    public StudentDto registerStudent(StudentDto studentDto);
    public StudentDto getStudent(String studentId);
    public StudentDto deleteStudent(String studentId);
    public List<StudentDto> allStudent();
    public AnalysisDto getAnalysis();

}
