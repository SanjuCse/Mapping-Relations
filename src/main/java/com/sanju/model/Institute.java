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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Institute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "institute_id")
	private Integer institute_id;

	@Nonnull
	@Column(name = "institute_name")
	private String instituteName;

//	@Nonnull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL/*, mappedBy = "institute_id"*/)
//	@JoinColumn(referencedColumnName = "institute")
	private List<Student> students;
}