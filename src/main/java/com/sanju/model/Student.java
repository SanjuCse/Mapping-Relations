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
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Integer studentId;

	@Nonnull
	@Column(name = "student_name")
	private String studentName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "institute_id"/*, referencedColumnName = "institute_id"*/)
	private Institute institute_id;

	@ManyToMany(cascade = CascadeType.ALL/* , mappedBy = "students" */)
	private List<Course> courses;
}
