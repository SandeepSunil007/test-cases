package com.code.student.service;

import java.util.List;

import com.code.student.entity.Student;
import com.code.student.request.StudentRequest;

public interface StudentService {
	public Student addStudent(StudentRequest stuRequest);

	public Student getStudentById(String id);

	public Student updateStudent(String id, StudentRequest stuRequest);

	public String deleteStudent(String id);

	public List<Student> getAllStudents();
}
