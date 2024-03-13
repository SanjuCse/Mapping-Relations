package com.sanju.repo;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanju.model.Student;
import com.sanju.response.StudentProcResponse;

import jakarta.transaction.Transactional;

@Transactional
public interface StudentRepo extends JpaRepository<Student, Integer> {

	@Query(value = "CALL GET_STUDENT_DATA();", nativeQuery = true)
//	@Procedure(procedureName = "GET_STUDENT_DATA")
//	List<Student> getStudentData();
	Map<String, Object> getStudentData();
//	StudentProcResponse getStudentData();
}
