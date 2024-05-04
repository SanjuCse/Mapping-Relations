package com.sanju.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NamedStoredProcedureQuery(
		name = "Car.getTotalCardsbyModelEntity", 
		procedureName = "GET_TOTAL_CARS_BY_MODEL", 
		parameters = {
				@StoredProcedureParameter(
						mode = ParameterMode.IN, 
						name = "model_in", 
						type = String.class
						),
				@StoredProcedureParameter(
						mode = ParameterMode.OUT, 
						name = "count_out", 
						type = Integer.class
						) 
				}
)

@Table(name = "CAR_DETAILS")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAR_ID")
	private Long id;

	@Column(name = "CAR_MODEL", length = 50)
	private String model;

	@Column(name = "MANUFACTURE_YEAR")
	private Integer year;
}