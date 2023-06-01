package com.example.restclient.service;

import com.example.grpcproto.v1.*;
import com.example.restclient.dto.AnalysisDto;
import com.example.restclient.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentServiceGrpc.StudentServiceBlockingStub javaServerBlockingStub;
    private final StudentServiceGrpc.StudentServiceBlockingStub pythonServerBlockingStub;

    public StudentServiceImpl(@Qualifier("name=javaStub") StudentServiceGrpc.StudentServiceBlockingStub javaServerBlockingStub,
                              @Qualifier("name=pythonStub") StudentServiceGrpc.StudentServiceBlockingStub pythonServerBlockingStub) {

        this.javaServerBlockingStub = javaServerBlockingStub;
        this.pythonServerBlockingStub = pythonServerBlockingStub;
    }

    @Override
    public StudentDto registerStudent(StudentDto studentDto) {

        RegisterStudentRequest request = RegisterStudentRequest.newBuilder()
                .setStudentId(studentDto.getStudentId())
                .setName(studentDto.getName())
                .setSemester(studentDto.getSemester())
                .setDept(studentDto.getDept())
                .setCgpa(studentDto.getCgpa())
                .build();

        StudentResponse response = javaServerBlockingStub.registerStudent(request);
        return convertProtobufToDto(response);
    }

    @Override
    public StudentDto getStudent(String studentId) {

        StudentRequest request = StudentRequest.newBuilder().setStudentId(studentId).build();

        StudentResponse response = javaServerBlockingStub.getStudentById(request);
        return convertProtobufToDto(response);
    }

    @Override
    public StudentDto deleteStudent(String studentId) {

        return convertProtobufToDto(javaServerBlockingStub.deleteStudentById(
                StudentRequest.newBuilder()
                        .setStudentId(studentId)
                        .build()));
    }

    @Override
    public List<StudentDto> allStudent() {

        EmptyRequest request = EmptyRequest.newBuilder().build();
        AllStudentResponse response = javaServerBlockingStub.getAllStudent(request);

        List<StudentDto> studentDtoList = new ArrayList<>();
        response.getStudentResponseListList().forEach(studentResponse -> studentDtoList.add(convertProtobufToDto(studentResponse)));
        return studentDtoList;
    }

    private StudentDto convertProtobufToDto(StudentResponse response) {
        return StudentDto.builder()
                .studentId(response.getStudentId())
                .name(response.getName())
                .dept(response.getDept())
                .semester(response.getSemester())
                .cgpa(response.getCgpa())
                .build();
    }


    @Override
    public AnalysisDto getAnalysis() {
        StudentRequest request = StudentRequest.newBuilder().setStudentId("223").build();

        StudentResponse response = pythonServerBlockingStub.getStudentById(request);
        log.info(":::: {}", convertProtobufToDto(response));
        return null;
    }


    private void shutDownChannel() {
//        javaChannel.shutdown();
//        pythonChannel.shutdown();
    }

}
