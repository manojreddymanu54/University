package com.example.university.repository;

import java.util.*;
import com.example.university.model.Student;
import com.example.university.model.Course;

public interface StudentRepository {
    public ArrayList<Student> getStudents();

    public Student getStudentById(int studentId);

    public Student addStudent(Student student);

    public Student updateStudent(int studentId, Student student);

    public void deleteStudent(int studentId);

    public List<Course> getStudentCourses(int studentId);
}