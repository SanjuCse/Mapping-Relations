package com.sanju.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StudentProcResponse {
	private String instituteName;
    private String studentCity;
    private String studentCountry;
    private String studentState;
    private String courseName;
    private Integer studentId;
    private String studentName;
}
