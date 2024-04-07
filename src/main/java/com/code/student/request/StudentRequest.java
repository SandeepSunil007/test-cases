package com.code.student.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentRequest {
	private String studentId;
	private String studentName;
	private String rollNumber;

	private int age;
	private String studying;
	private int marks;

}
