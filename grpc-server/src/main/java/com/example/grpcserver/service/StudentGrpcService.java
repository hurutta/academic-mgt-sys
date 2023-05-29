package com.example.grpcserver.service;

import com.example.grpcproto.v1.StudentRequest;
import com.example.grpcproto.v1.StudentResponse;
import io.grpc.stub.StreamObserver;

public interface StudentGrpcService {
    void getStudentById(StudentRequest request, StreamObserver<StudentResponse> responseObserver);
}
