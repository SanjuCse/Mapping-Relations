package com.sanju.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
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

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESS_DETAILS")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID")
	private Integer addressId;

	@Nonnull
	@Column(name = "CITY_NAME")
	private String city;

	@Nonnull
	@Column(name = "STATE_NAME")
	private String state;

	@Nonnull
	@Column(name = "COUNTRY_NAME")
	private String country;

//	@Nonnull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "INSTITUTE_ID")
	private Institute instituteId;
}
