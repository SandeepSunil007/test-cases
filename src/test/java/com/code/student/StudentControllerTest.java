package com.code.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.code.student.controller.StudentController;
import com.code.student.entity.Student;
import com.code.student.request.StudentRequest;
import com.code.student.response.StudentResponseBody;
import com.code.student.service.StudentService;

@SpringBootTest
public class StudentControllerTest {

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	@Test
	void testAddStudentDetails() {
		StudentRequest request = new StudentRequest("1","Jane Doe", "456", 21, "Computer Science", 95);
		Student student = new Student("1", "Jane Doe", "456", 21, "Computer Science", 95);

		when(studentService.addStudent(request)).thenReturn(student);

		ResponseEntity<StudentResponseBody> responseEntity = studentController.addStudentDetails(request);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertFalse(responseEntity.getBody().isError());
		assertEquals("We added the data into database sucessfully", responseEntity.getBody().getMessage());
		assertEquals(student, responseEntity.getBody().getData());
	}

	@Test
	void testGetStudentById() {
		String id = "1";
		Student student = new Student(id, "Jane Doe", "456", 21, "Computer Science", 95);

		when(studentService.getStudentById(id)).thenReturn(student);

		ResponseEntity<StudentResponseBody> responseEntity = studentController.getStudentById(id);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertFalse(responseEntity.getBody().isError());
		assertEquals("We get the data from database", responseEntity.getBody().getMessage());
		assertEquals(student, responseEntity.getBody().getData());
	}

	@Test
	void testUpdateStudent() {
		String id = "1";
		StudentRequest request = new StudentRequest("1","Jane Doe", "456", 21, "Computer Science", 95);
		Student updatedStudent = new Student(id, "Jane Doe", "456", 21, "Computer Science", 95);

		when(studentService.updateStudent(id, request)).thenReturn(updatedStudent);

		ResponseEntity<StudentResponseBody> responseEntity = studentController.updateStudent(id, request);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertFalse(responseEntity.getBody().isError());
		assertEquals("We updated the data succesfully", responseEntity.getBody().getMessage());
		assertEquals(updatedStudent, responseEntity.getBody().getData());
	}

	@Test
	void testDeleteStudent() {
		String id = "1";
		String message = id + " deleted successfully";

		when(studentService.deleteStudent(id)).thenReturn(message);

		ResponseEntity<StudentResponseBody> responseEntity = studentController.deleteStudent(id);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertFalse(responseEntity.getBody().isError());
		assertEquals(message, responseEntity.getBody().getMessage());
		assertEquals(null, responseEntity.getBody().getData());
	}

	@Test
	void testGetAllStudents() {
		List<Student> students = Arrays.asList(new Student("1", "John Doe", "123", 20, "Computer Science", 90),
				new Student("2", "Jane Doe", "456", 21, "Computer Science", 95));

		when(studentService.getAllStudents()).thenReturn(students);

		List<Student> result = studentController.getAllStudents();

		assertEquals(students.size(), result.size());
		assertTrue(result.containsAll(students));
	}
}
