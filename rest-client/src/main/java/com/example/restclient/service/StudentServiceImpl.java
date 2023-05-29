package com.example.restclient.service;

import com.example.grpcproto.v1.RegisterStudentRequest;
import com.example.grpcproto.v1.StudentRequest;
import com.example.grpcproto.v1.StudentResponse;
import com.example.grpcproto.v1.StudentServiceGrpc;
import com.example.restclient.dto.StudentDto;
import io.grpc.ManagedChannel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private StudentServiceGrpc.StudentServiceBlockingStub blockingStub;
    private ManagedChannel channel;

    @Override
    public StudentDto registerStudent(StudentDto studentDto) {

        RegisterStudentRequest request = RegisterStudentRequest.newBuilder()
                .setStudentId(studentDto.getStudentId())
                .setName(studentDto.getName())
                .setSemester(studentDto.getSemester())
                .setDept(studentDto.getDept())
                .setCgpa(studentDto.getCgpa())
                .build();

        StudentResponse response = blockingStub.registerStudent(request);
        return convertProtobufToDto(response);
    }

    @Override
    public StudentDto getStudent(String studentId) {

        StudentRequest request = StudentRequest.newBuilder().setStudentId(studentId).build();

        StudentResponse response = blockingStub.getStudentById(request);
        return convertProtobufToDto(response);
    }

    @Override
    public StudentDto deleteStudent(String studentId) {

        return convertProtobufToDto(blockingStub.deleteStudentById(
                StudentRequest.newBuilder()
                        .setStudentId(studentId)
                        .build()));
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

    private void shutDownChannel() {
        channel.shutdown();
    }

}
