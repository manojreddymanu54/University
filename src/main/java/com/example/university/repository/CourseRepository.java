package com.example.university.repository;

import java.util.*;
import com.example.university.model.Course;
import com.example.university.model.Student;
import com.example.university.model.Professor;

public interface CourseRepository {
    public ArrayList<Course> getCourses();

    public Course getCourseById(int courseId);

    public Course addCourse(Course course);

    public Course updateCourse(int courseId, Course course);

    public void deleteCourse(int courseId);

    public Professor getCourseProfessor(int courseId);

    public List<Student> getCourseStudents(int courseId);
}
