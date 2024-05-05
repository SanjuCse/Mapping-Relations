package com.sanju.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanju.model.Course;
import com.sanju.model.Institute;
import com.sanju.model.Student;
import com.sanju.repo.GenericRepo;
import com.sanju.repo.StudentRepo;
import com.sanju.response.StudentRequest;
import com.sanju.service.StudentService;

@RestController
public class TestController {

	@Autowired
	private GenericRepo genericRepo;
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/students/details")
	private ResponseEntity<Object> studentsDetails() {
		List<Map<String, Object>> studentData = genericRepo.getStudentData();
//		List<StudentProcResponse> respList = new ArrayList<>();
		List<Map<String, Object>> respMap = new ArrayList<>();

		for (Map<String, Object> studentProc : studentData) {
			Map<String, Object> studentResp = new HashMap<>();
			for (String key : studentProc.keySet()) {
				studentResp.put(doCamelCase(key), studentProc.get(key));
			}
			respMap.add(studentResp);
		}

		return new ResponseEntity<Object>(respMap, HttpStatus.OK);
//		return new ResponseEntity<Object>(studentData, HttpStatus.OK);
	}

	@GetMapping("/cars/after/{year}")
	private ResponseEntity<Object> getCars(@PathVariable Integer year) {
		List<Object> cars = genericRepo.findCarsAfterYear(year);
		return new ResponseEntity<Object>(cars, HttpStatus.OK);
	}

	@DeleteMapping("/delete/student/{studentId}")
	private ResponseEntity<Object> deleteStudent(@PathVariable Integer studentId) {
		
		studentService.deleteStudent(studentId);
		
		return new ResponseEntity<Object>("Student Deleted Successfully", HttpStatus.OK);
	}

	@PostMapping("/register/student")
	private ResponseEntity<Object> registerStudent(@RequestBody StudentRequest studentReq) {
		
		studentService.registerStudent(studentReq);
		
		return new ResponseEntity<Object>("Student Saved Successfully", HttpStatus.OK);
	}

	private String doCamelCase(String originalString) {
		String[] parts = originalString.split("_");
		StringBuilder convertedString = new StringBuilder(parts[0]);
		for (int i = 1; i < parts.length; i++) {
			convertedString.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1));
		}
		return convertedString.toString();
	}
}
