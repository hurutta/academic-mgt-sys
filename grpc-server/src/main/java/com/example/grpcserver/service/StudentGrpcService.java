package com.example.grpcserver.service;

import com.example.grpcproto.v1.RegisterStudentRequest;
import com.example.grpcproto.v1.StudentRequest;
import com.example.grpcproto.v1.StudentResponse;
import io.grpc.stub.StreamObserver;

public interface StudentGrpcService {
    public void getStudentById(StudentRequest request, StreamObserver<StudentResponse> responseObserver);
    public void registerStudent(RegisterStudentRequest request, StreamObserver<StudentResponse> responseObserver);
    public void deleteStudentById(StudentRequest request, StreamObserver<StudentResponse> responseObserver);
}
