package com.sanju.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanju.model.Course;
import com.sanju.model.Institute;
import com.sanju.model.Student;
import com.sanju.repo.StudentRepo;
import com.sanju.response.StudentRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService {
	@Autowired
	private StudentRepo studentRepo;
	
	public void registerStudent(StudentRequest studentReq) {
		Student student = new Student();
		List<Course> courses = new ArrayList<>();
		if (studentReq.getCourseIds() != null) {
			for (Integer courseId : studentReq.getCourseIds()) {
				Course course = new Course();
				course.setCourseId(courseId);
				courses.add(course);
			}
		}
		student.setCourses(courses);
		BeanUtils.copyProperties(studentReq, student);

		if (studentReq.getInstituteId() != null) {
			Institute institute = new Institute();
			institute.setInstituteId(studentReq.getInstituteId());

			student.setInstituteId(institute);
		}

		studentRepo.save(student);
	}
	
	public void deleteStudent(Integer studentId) {
		studentRepo.deleteById(studentId);
	}
}
