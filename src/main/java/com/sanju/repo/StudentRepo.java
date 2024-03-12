package com.sanju.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.sanju.model.Student;

import jakarta.transaction.Transactional;

@Transactional
public interface StudentRepo extends JpaRepository<Student, Integer> {

//	@Query(value = "CALL GET_STUDENT_DATA();", nativeQuery = true)
	@Procedure(procedureName = "GET_STUDENT_DATA")
	List<Student> getStudentData();
}
