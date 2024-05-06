package com.sanju.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CarRequest {
	private Long id;

	private String model;

	private Integer year;
}
