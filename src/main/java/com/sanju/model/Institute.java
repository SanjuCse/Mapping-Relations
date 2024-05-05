package com.sanju.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "INSTITUTE_DETAILS")
public class Institute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INSTITUTE_ID")
	private Integer instituteId;

	@Nonnull
	@Column(name = "INSTITUTE_NAME")
	private String instituteName;

//	@Nonnull
	@OneToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Student> students;
}