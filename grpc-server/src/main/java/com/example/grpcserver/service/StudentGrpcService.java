package com.example.grpcserver.service;

import com.example.grpcproto.v1.*;
import io.grpc.stub.StreamObserver;

public interface StudentGrpcService {
    public void getStudentById(StudentRequest request, StreamObserver<StudentResponse> responseObserver);
    public void registerStudent(RegisterStudentRequest request, StreamObserver<StudentResponse> responseObserver);
    public void deleteStudentById(StudentRequest request, StreamObserver<StudentResponse> responseObserver);
    public void getAllStudent(EmptyRequest request, StreamObserver<AllStudentResponse> responseObserver);
}
