package com.sanju.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StudentProcResponse {
	private String student_name;
	private Long course_id;
	private String institute_state;
	private String course_name;
	private String institute_country;
	private String student_city;
	private String student_state;
	private String institute_city;
	private Long student_id;
	private String student_country;
	private Long institute_id;
	private String institute_name;
}
