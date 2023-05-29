package com.example.grpcserver.service;

import com.example.grpcproto.v1.*;
import com.example.grpcserver.model.Student;
import com.example.grpcserver.repository.StudentRepository;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@GrpcService
public class StudentGrpcServiceImpl extends StudentServiceGrpc.StudentServiceImplBase implements StudentGrpcService {

    private StudentRepository studentRepository;

    @Override
    public void getStudentById(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        Student student = studentRepository.findStudentsByStudentId(request.getStudentId());
        StudentResponse response = getStudentResponse(student);

        log.info("StudentGrpcServiceImpl:: getStudentById:: response:: {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void registerStudent(RegisterStudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        Student student = studentRepository.save(Student.builder()
                .name(request.getName())
                .studentId(request.getStudentId())
                .semester(request.getSemester())
                .dept(request.getDept())
                .cgpa(request.getCgpa())
                .build());
        StudentResponse response = getStudentResponse(student);

        log.info("StudentGrpcServiceImpl:: registerStudent:: response:: {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteStudentById(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        Student student = studentRepository.deleteStudentByStudentId(request.getStudentId());
        StudentResponse response = getStudentResponse(student);

        log.info("StudentGrpcServiceImpl:: registerStudent:: response:: {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllStudent(EmptyRequest request, StreamObserver<AllStudentResponse> responseObserver) {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponseList = new ArrayList<>();
        students.forEach(student -> {
            studentResponseList.add(getStudentResponse(student));
        });
        AllStudentResponse response = AllStudentResponse.newBuilder().addAllStudentResponseList(studentResponseList).build();

        log.info("StudentGrpcServiceImpl:: registerStudent:: response:: {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private static StudentResponse getStudentResponse(Student student) {
        return StudentResponse.newBuilder()
                .setStudentId(student.getStudentId())
                .setName(student.getName())
                .setSemester(student.getSemester())
                .setCgpa(student.getCgpa())
                .setDept(student.getDept())
                .build();
    }
}
