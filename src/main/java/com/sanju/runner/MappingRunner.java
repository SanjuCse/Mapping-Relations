package com.sanju.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sanju.model.Address;
import com.sanju.model.Course;
import com.sanju.model.Institute;
import com.sanju.model.Student;
import com.sanju.repo.AddressRepo;
import com.sanju.repo.InstituteRepo;

@Component
public class MappingRunner implements CommandLineRunner {

	@Autowired
	private InstituteRepo instituteRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Override
	public void run(String... args) throws Exception {
		Student sanju = new Student("sanju");
		Student balia = new Student("balia");

		Institute nareshit = new Institute("Durga Soft");
		Address instituteAddress = new Address("Hyd", "Telengana", "India");
		instituteAddress.setInstitute(nareshit);

		nareshit.setAddress(instituteAddress);
		nareshit.setStudents(List.of(sanju, balia));

		sanju.setInstitute(nareshit);
		balia.setInstitute(nareshit);

		Course java = new Course("java");
		Course python = new Course("python");

		java.setStudents(List.of(sanju));
		python.setStudents(List.of(balia));

		sanju.setCourses(List.of(java));
		balia.setCourses(List.of(python));
//		if (addressRepo.save(instituteAddress).getAddressId() != null) {
//			System.out.println("Address saved Successfully");
//		} else {
//			System.out.println("Unable to save Address");
//		}

//		if (instituteRepo.save(nareshit).getInstituteId() != null) {
//			System.out.println("Institute saved Successfully");
//		} else {
//			System.out.println("Unable to save Institute");
//		}
		
		addressRepo.deleteById(2);
	}

}
