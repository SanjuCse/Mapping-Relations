package com.sanju.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sanju.model.Student;

public interface GenericRepo extends JpaRepository<Student, Integer> {

	@Query(value = "CALL GET_STUDENT_DATA();", nativeQuery = true)
	List<Map<String, Object>> getStudentData();

	@Query(value = "CALL FIND_CARS_AFTER_YEAR(:year_in);", nativeQuery = true)
	List<Object> findCarsAfterYear(@Param("year_in") Integer year);
}
