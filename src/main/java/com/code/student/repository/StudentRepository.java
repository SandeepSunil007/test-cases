package com.code.student.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.code.student.entity.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

}
