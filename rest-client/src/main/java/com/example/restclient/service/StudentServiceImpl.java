package com.example.restclient.service;

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

    StudentServiceGrpc.StudentServiceBlockingStub blockingStub;

    @Override
    public StudentDto getStudent(String studentId) {


        StudentRequest request = StudentRequest.newBuilder().setStudentId(studentId).build();

        StudentResponse response = blockingStub.getStudentById(request);
        //channel.shutdown();

        return convertProtobufToDto(response);
    }

    private StudentDto convertProtobufToDto(StudentResponse response) {
        return StudentDto.builder()
                .studentId(response.getStudentId())
                .name(response.getStudentName())
                .build();
    }
}
