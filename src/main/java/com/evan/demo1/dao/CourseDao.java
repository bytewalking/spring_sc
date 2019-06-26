package com.evan.demo1.dao;

import com.evan.demo1.entity.Course;
import com.evan.demo1.entity.Student;

import java.util.List;

public interface CourseDao {

    void insert(Course course);

    void update(Course course);

    void delete(int id);

    Course select(int id);

    List<Course> selectAll();

}
