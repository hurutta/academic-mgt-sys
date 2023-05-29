package com.example.grpcserver.repository;

import com.example.grpcserver.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findStudentsByStudentId(String studentId);

}
