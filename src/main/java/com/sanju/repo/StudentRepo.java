package com.sanju.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanju.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

//	@Procedure(procedureName = "GET_STUDENT_DATA")
//	List<Student> getStudentData();
//	StudentProcResponse getStudentData();
	
	@Query(value = "CALL GET_STUDENT_DATA();", nativeQuery = true)
	List<Map<String, Object>> getStudentData();
}
