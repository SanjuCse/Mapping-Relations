package com.sanju.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanju.model.Car;
import com.sanju.repo.CarRepository;
import com.sanju.response.CarRequest;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	
	public Boolean saveCar(CarRequest carRequest) {
		Car car = new Car();
		BeanUtils.copyProperties(carRequest, car);
		carRepository.save(car);
		
		return car.getId() != null;
	}
}
