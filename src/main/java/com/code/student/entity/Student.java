package com.code.student.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "Student_table" )
public class Student {
	@Id
	private String studentId;
	private String studentName;
	private String rollNumber;

	private int age;
	private String studying;
	private int marks;

}
