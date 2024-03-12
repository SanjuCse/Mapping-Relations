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
	@Column(name = "address_id")
	private Integer addressId;

	@Nonnull
	@Column(name = "city")
	private String city;

	@Nonnull
	@Column(name = "state")
	private String state;

	@Nonnull
	@Column(name = "country")
	private String country;

//	@Nonnull
	@OneToOne(cascade = CascadeType.ALL /* , mappedBy = "address" */)
	@JoinColumn(name = "institute_id"/*, referencedColumnName = "institute_id"*/)
	private Institute institute_id;
}
