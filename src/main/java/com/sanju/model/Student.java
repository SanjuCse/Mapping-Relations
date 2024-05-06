package com.sanju.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
//@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "STUDENT_DETAILS")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
	private Integer studentId;

	@Nonnull
	@Column(name = "STUDENT_NAME")
	private String studentName;

	@JsonManagedReference
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "INSTITUTE_ID"/* , referencedColumnName = "institute_id" */)
	private Institute instituteId;

	@ManyToMany//(cascade = CascadeType.ALL/* , mappedBy = "students" */)
	private List<Course> courses;

//	@Override
//	public String toString() {
//		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", instituteId=" + instituteId
//				+ ", courses=" + courses + "]";
//	}
}
