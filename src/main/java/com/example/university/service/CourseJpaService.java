package com.example.university.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.model.Course;
import com.example.university.model.Student;
import com.example.university.model.Professor;
import com.example.university.repository.ProfessorJpaRepository;
import com.example.university.repository.StudentJpaRepository;

@Service
public class CourseJpaService implements CourseRepository {
	@Autowired
	private CourseJpaRepository courseJpaRepository;

	private ProfessorJpaRepository professorJpaRepository;

	private StudentJpaRepository studentJpaRepository;

	@Override
	public ArrayList<Course> getCourses() {
		List<Course> course = courseJpaRepository.findAll();
		ArrayList<Course> allcourses = new ArrayList<>(course);
		return allcourses;
	}

	@Override
	public Course getCourseById(int courseId) {
		try{
		Course course = courseJpaRepository.findById(courseId).get();
		return course;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Course addCourse(Course course) {
		try {
			int professorId = course.getProfessor().getProfessorId();
			Professor professor = professorJpaRepository.findById(professorId).get();
			course.setProfessor(professor);

			List<Integer> studentIds = new ArrayList<>();

			for (Student student : course.getStudents()) {
				studentIds.add(student.getStudentId());
			}

			List<Student> students = studentJpaRepository.findAllById(studentIds);

			if (students.size() != studentIds.size()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			course.setStudents(students);

			return courseJpaRepository.save(course);

		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Course updateCourse(int courseId, Course course) {
		try {
			Course oldCourse = courseJpaRepository.findById(courseId).get();
			if (course.getCourseName() != null) {
				oldCourse.setCourseName(course.getCourseName());
			}

			if (course.getCredits() != 0) {
				oldCourse.setCredits(course.getCredits());
			}

			if (course.getProfessor() != null) {
				int professorId = course.getProfessor().getProfessorId();
				Professor professor = professorJpaRepository.findById(professorId).get();
				oldCourse.setProfessor(professor);
			}
			if (course.getStudents() != null) {
				List<Integer> studentIds = new ArrayList<>();

				for (Student student : course.getStudents()) {
					studentIds.add(student.getStudentId());
				}

				List<Student> students = studentJpaRepository.findAllById(studentIds);
				if (students.size() != studentIds.size()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				}
				oldCourse.setStudents(students);
			}
			return courseJpaRepository.save(oldCourse);

		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void deleteCourse(int courseId) {
		try {
			courseJpaRepository.deleteById(courseId);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Professor getCourseProfessor(int courseId) {
		try {
			Course course = courseJpaRepository.findById(courseId).get();
			Professor professor = course.getProfessor();
			return professor;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	public List<Student> getCourseStudents(int courseId) {
		try {
			Course course = courseJpaRepository.findById(courseId).get();
			List<Student> students = course.getStudents();
			return students;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
