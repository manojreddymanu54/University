package com.example.university.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import com.example.university.repository.ProfessorRepository;
import com.example.university.repository.ProfessorJpaRepository;
import com.example.university.model.Course;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.model.Professor;

@Service
public class ProfessorJpaService implements ProfessorRepository {
	@Autowired
	private ProfessorJpaRepository professorJpaRepository;

	@Autowired
	private CourseJpaRepository courseJpaRepository;

	@Override
	public ArrayList<Professor> getProfessors() {
		List<Professor> professor = professorJpaRepository.findAll();
		ArrayList<Professor> allprofessors = new ArrayList<>(professor);
		return allprofessors;
	}

	@Override
	public Professor getProfessorById(int professorId) {
		try {
			Professor professor = professorJpaRepository.findById(professorId).get();
			return professor;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Professor addProfessor(Professor professor) {
		return professorJpaRepository.save(professor);

	}

	@Override
	public Professor updateProfessor(int professorId, Professor professor) {
		try {
			Professor oldProfessor = professorJpaRepository.findById(professorId).get();
			if (professor.getProfessorName() != null) {
				oldProfessor.setProfessorName(professor.getProfessorName());
			}

			if (professor.getDepartment() != null) {
				oldProfessor.setDepartment(professor.getDepartment());
			}
			return professorJpaRepository.save(oldProfessor);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void deleteProfessor(int professorId) {
		try {
			Professor professor = professorJpaRepository.findById(professorId).get();
            List<Course> courses = courseJpaRepository.findByProfessor(professor);
            for (Course course : courses) {
                course.setProfessor(null);
            }
            courseJpaRepository.saveAll(courses);
            professorJpaRepository.deleteById(professorId);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ArrayList<Course> getProfessorCourses(int professorId) {
		try {
			Professor professor = professorJpaRepository.findById(professorId).get();
			ArrayList<Course> courses = courseJpaRepository.findByProfessor(professor);
			return courses;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}