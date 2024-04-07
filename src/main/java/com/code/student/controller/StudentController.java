package com.code.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.student.entity.Student;
import com.code.student.request.StudentRequest;
import com.code.student.response.StudentResponseBody;
import com.code.student.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

//	@PostMapping("/addStudent")
//	public ResponseEntity<Student> addStudentDetails(@RequestBody StudentRequest studentRequest) {
//		Student student = studentService.addStudent(studentRequest);
//		return ResponseEntity.status(HttpStatus.CREATED).body(student);
//
//	}

	@PostMapping("/addStudent")
	public ResponseEntity<StudentResponseBody> addStudentDetails(@RequestBody StudentRequest studentRequest) {

		return ResponseEntity
				.ok(StudentResponseBody.builder().isError(false).message("We added the data into database sucessfully")
						.data(studentService.addStudent(studentRequest)).build());

	}

	@GetMapping("/students/fetch/{id}")
	public ResponseEntity<StudentResponseBody> getStudentById(@PathVariable String id) {
		return ResponseEntity.ok(StudentResponseBody.builder().isError(false).message("We get the data from database")
				.data(studentService.getStudentById(id)).build());
	}

	@PutMapping("/students/update/{id}")
	public ResponseEntity<StudentResponseBody> updateStudent(@PathVariable String id,
			@RequestBody StudentRequest studentRequest) {
		return ResponseEntity.ok(StudentResponseBody.builder().isError(false).message("We updated the data succesfully")
				.data(studentService.updateStudent(id, studentRequest)).build());
	}

	@DeleteMapping("/students/delete/{id}")
	public ResponseEntity<StudentResponseBody> deleteStudent(@PathVariable String id) {
		return ResponseEntity
				.ok(StudentResponseBody.builder().isError(false).message(studentService.deleteStudent(id)).build());
	}

	@GetMapping("/students/getAll")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
}
