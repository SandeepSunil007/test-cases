package com.code.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.student.entity.Student;
import com.code.student.exception.StudentIdNotFoundException;
import com.code.student.repository.StudentRepository;
import com.code.student.request.StudentRequest;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student addStudent(StudentRequest stuRequest) {

		Student student = Student.builder().studentName(stuRequest.getStudentName())
				.rollNumber(stuRequest.getRollNumber()).age(stuRequest.getAge()).studying(stuRequest.getStudying())
				.marks(stuRequest.getMarks()).build();
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(String id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new StudentIdNotFoundException("Student not found with id: " + id));
	}

	@Override
	public Student updateStudent(String id, StudentRequest stuRequest) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentIdNotFoundException("Student not found with id: " + id));
		existingStudent.setStudentName(stuRequest.getStudentName());
		existingStudent.setRollNumber(stuRequest.getRollNumber());
		existingStudent.setAge(stuRequest.getAge());
		existingStudent.setStudying(stuRequest.getStudying());
		existingStudent.setMarks(stuRequest.getMarks());
		return studentRepository.save(existingStudent);

	}

	@Override
	public String deleteStudent(String id) {
		Student exstingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new StudentIdNotFoundException("Student not found with id: " + id));
		studentRepository.delete(exstingStudent);
		return id + " deleted succesfully";
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

}
