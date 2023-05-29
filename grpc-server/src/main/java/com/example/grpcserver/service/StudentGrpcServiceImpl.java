package com.example.grpcserver.service;

import com.example.grpcproto.v1.StudentRequest;
import com.example.grpcproto.v1.StudentResponse;
import com.example.grpcproto.v1.StudentServiceGrpc;
import com.example.grpcserver.model.Student;
import com.example.grpcserver.repository.StudentRepository;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@AllArgsConstructor
@GrpcService
public class StudentGrpcServiceImpl extends StudentServiceGrpc.StudentServiceImplBase implements StudentGrpcService {

    private StudentRepository studentRepository;

    @Override
    public void getStudentById(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        StudentResponse response = StudentResponse.newBuilder()
                .setStudentId(request.getStudentId())
                .setStudentName("testing name")
                .build();

        studentRepository.save(Student.builder().studentId(request.getStudentId()).build());
        log.info("StudentGrpcServiceImpl:: getStudentById:: response:: {}", response);
        log.info("StudentGrpcServiceImpl:: getStudentById:: response:: {}", studentRepository.findAll());


        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
