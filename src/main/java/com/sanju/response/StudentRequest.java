package com.sanju.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
public class StudentRequest {
	private Integer studentId;

	private String studentName;

	private Integer instituteId;

	private List<Integer> courseIds;
}
