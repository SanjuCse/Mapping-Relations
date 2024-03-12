package com.sanju.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sanju.model.Address;
import com.sanju.model.Car;
import com.sanju.model.Course;
import com.sanju.model.Institute;
import com.sanju.model.Student;
import com.sanju.repo.AddressRepo;
import com.sanju.repo.CarRepository;
import com.sanju.repo.InstituteRepo;
import com.sanju.repo.StudentRepo;

@Component
public class MappingRunner implements CommandLineRunner {

	@Autowired
	private InstituteRepo instituteRepo;

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public void run(String... args) throws Exception {
		Student sanju = new Student("sanju");
		Student prakash = new Student("prakash");

		Institute itSoft = new Institute("XYZ Institute");
		Address instituteAddress = new Address("Hyd", "Telengana", "India");
		instituteAddress.setInstitute_id(itSoft);

		itSoft.setAddress(instituteAddress);
		itSoft.setStudents(List.of(sanju, prakash));

		sanju.setInstitute_id(itSoft);
		prakash.setInstitute_id(itSoft);

		Course java = new Course("java");
		Course python = new Course("python");

		java.setStudents(List.of(sanju));
		python.setStudents(List.of(prakash));

		sanju.setCourses(List.of(java));
		prakash.setCourses(List.of(python));
		
//		if (addressRepo.save(instituteAddress).getAddressId() != null) {
//			System.out.println("Address saved Successfully");
//		} else {
//			System.out.println("Unable to save Address");
//		}
		

//		if (instituteRepo.save(itSoft).getInstitute_id() != null) {
//			System.out.println("Institute saved Successfully");
//		} else {
//			System.out.println("Unable to save Institute");
//		}
		
//		List<Car> carsAfter2020 = carRepository.findCarsAfterYear(2020);
//		carsAfter2020.stream().forEach(System.out::println);
		
		List<Student> studentData = studentRepo.getStudentData();
		studentData.stream().forEach(System.out::println);
		
//		addressRepo.deleteById(2);
	}

}
