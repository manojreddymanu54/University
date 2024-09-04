package com.example.university.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import com.example.university.repository.StudentRepository;
import com.example.university.repository.StudentJpaRepository;
import com.example.university.model.Course;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.model.Student;

@Service
public class StudentJpaService implements StudentRepository {
	@Autowired
	private StudentJpaRepository studentJpaRepository;

	@Autowired
	private CourseJpaRepository courseJpaRepository;

	@Override
	public ArrayList<Student> getStudents() {
		List<Student> student = studentJpaRepository.findAll();
		ArrayList<Student> allstudents = new ArrayList<>(student);
		return allstudents;
	}

	@Override
	public Student getStudentById(int studentId) {
		try{
		Student student = studentJpaRepository.findById(studentId).get();
		return student;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Student addStudent(Student student) {
		try {

			List<Integer> courseIds = new ArrayList<>();

			for (Course course : student.getCourses()) {
				courseIds.add(course.getCourseId());
			}

			List<Course> courses = courseJpaRepository.findAllById(courseIds);
			if (courses.size() != courseIds.size()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			student.setCourses(courses);

			return studentJpaRepository.save(student);

		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Student updateStudent(int studentId, Student student) {
		try {
			Student oldStudent = studentJpaRepository.findById(studentId).get();
			if (student.getStudentName() != null) {
				oldStudent.setStudentName(student.getStudentName());
			}

			if (student.getEmail() != null) {
				oldStudent.setEmail(student.getEmail());
			}

			if (student.getCourses() != null) {
				List<Integer> courseIds = new ArrayList<>();

				for (Course course : student.getCourses()) {
					courseIds.add(course.getCourseId());
				}

				List<Course> courses = courseJpaRepository.findAllById(courseIds);
				if (courses.size() != courseIds.size()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				}
				oldStudent.setCourses(courses);

			}
			return studentJpaRepository.save(oldStudent);

		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void deleteStudent(int studentId) {
		try {
			studentJpaRepository.deleteById(studentId);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	public List<Course> getStudentCourses(int studentId) {
		try {
			Student student = studentJpaRepository.findById(studentId).get();
			List<Course> courses = student.getCourses();
			return courses;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}