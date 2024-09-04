package com.example.university.repository;

import java.util.*;
import com.example.university.model.Professor;
import com.example.university.model.Course;

public interface ProfessorRepository {
    public ArrayList<Professor> getProfessors();

    public Professor getProfessorById(int professorId);

    public Professor addProfessor(Professor professor);

    public Professor updateProfessor(int professorId, Professor professor);

    public void deleteProfessor(int professorId);

    public List<Course> getProfessorCourses(int professorId);
}