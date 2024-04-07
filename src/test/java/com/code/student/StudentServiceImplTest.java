package com.code.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.code.student.entity.Student;
import com.code.student.exception.StudentIdNotFoundException;
import com.code.student.repository.StudentRepository;
import com.code.student.request.StudentRequest;
import com.code.student.service.StudentServiceImpl;

@SpringBootTest
public class StudentServiceImplTest {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentServiceImpl studentService;

	@Test
	public void testAddStudent() {
		StudentRequest studentRequest = new StudentRequest();
		studentRequest.setStudentName("John Doe");
		studentRequest.setRollNumber("101");
		studentRequest.setAge(20);
		studentRequest.setStudying("Computer Science");
		studentRequest.setMarks(90);

		Student student = Student.builder().studentName(studentRequest.getStudentName())
				.rollNumber(studentRequest.getRollNumber()).age(studentRequest.getAge())
				.studying(studentRequest.getStudying()).marks(studentRequest.getMarks()).build();

		when(studentRepository.save(any(Student.class))).thenReturn(student);
		Student result = studentService.addStudent(studentRequest);

		assertEquals(student.getRollNumber(), result.getRollNumber());
		assertEquals(student.getStudentName(), result.getStudentName());

	}

	@Test
	public void testGetStudentById() {
		String id = "1";
		Student student = new Student(id, "John Doe", "123", 20, "Computer Science", 90);

		when(studentRepository.findById(id)).thenReturn(Optional.of(student));

		Student result = studentService.getStudentById(id);

		assertEquals(student, result);

	}

	@Test
	public void testGetStudentByIdNotFound() {
		String id = "1";

		when(studentRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(StudentIdNotFoundException.class, () -> studentService.getStudentById(id));
	}

	@Test
	public void testUpdateStudent() {
		String id = "1";
		StudentRequest request = new StudentRequest("1", "Jane Doe", "456", 21, "Computer Science", 95);
		Student existingStudent = new Student(id, "John Doe", "123", 20, "Computer Science", 90);

		when(studentRepository.findById(id)).thenReturn(Optional.of(existingStudent));
		when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));
		/*
		 * This part specifies the behavior to apply when the save() method is called.
		 * Instead of simply returning a pre-defined value like thenReturn(),
		 * thenAnswer() allows you to specify a custom behavior using a lambda
		 * expression. In this case, the lambda expression invocation ->
		 * invocation.getArgument(0) retrieves the first argument passed to the save()
		 * method and returns it.
		 * 
		 */

//        when(studentRepository.save(any(Student.class))).thenReturn(existingStudent);
		Student result = studentService.updateStudent(id, request);

		assertEquals(request.getStudentName(), result.getStudentName());
		assertEquals(request.getRollNumber(), result.getRollNumber());
		assertEquals(request.getAge(), result.getAge());
		assertEquals(request.getStudying(), result.getStudying());
		assertEquals(request.getMarks(), result.getMarks());
	}

	@Test
	public void testDeleteStudent() {
		String id = "1";
		Student existingStudent = new Student(id, "John Doe", "123", 20, "Computer Science", 95);

		when(studentRepository.findById(id)).thenReturn(Optional.of(existingStudent));

		String result = studentService.deleteStudent(id);

		assertEquals(id + " deleted succesfully", result);
		verify(studentRepository, times(1)).delete(existingStudent);
	}

	@Test
	public void testGetAllStudents() {
		List<Student> students = Arrays.asList(new Student("1", "John Doe", "123", 20, "Computer Science", 95),
				new Student("2", "Jane Doe", "456", 21, "Mechanical", 90));

		when(studentRepository.findAll()).thenReturn(students);

		List<Student> result = studentService.getAllStudents();

		assertEquals(students.size(), result.size());
		assertTrue(result.containsAll(students));
	}
}
