package com.sanju.model;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
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
//@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;

	@Nonnull
	private String studentName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "instituteId")
	private Institute institute;

	@ManyToMany(cascade = CascadeType.ALL/*, mappedBy = "students" */)
	private List<Course> courses;
}
