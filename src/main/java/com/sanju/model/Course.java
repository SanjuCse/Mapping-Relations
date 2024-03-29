package com.sanju.model;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Integer courseId;

	@Nonnull
	@Column(name = "course_name")
	private String courseName;

	@ManyToMany(cascade = CascadeType.ALL /* , mappedBy = "courses" */)
	private List<Student> students;
}