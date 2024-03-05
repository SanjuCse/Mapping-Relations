package com.sanju.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;

	@Nonnull
	private String city;

	@Nonnull
	private String state;

	@Nonnull
	private String country;

//	@Nonnull
	@OneToOne(cascade = CascadeType.ALL /* , mappedBy = "address" */)
	@JoinColumn(referencedColumnName = "instituteId")
	private Institute institute;
}
