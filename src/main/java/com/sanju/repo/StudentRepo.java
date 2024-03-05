package com.sanju.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanju.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
