package com.sanju.model;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
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
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "INSTITUTE_ID"/* , referencedColumnName = "institute_id" */)
	private Institute instituteId;

	@ManyToMany(cascade = CascadeType.ALL/* , mappedBy = "students" */)
	private List<Course> courses;
}
