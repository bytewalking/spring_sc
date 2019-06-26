package com.evan.demo1.dao;

import com.evan.demo1.entity.Student;

import java.util.List;

public interface StudentDao {

    void insert(Student student);

    void update(Student student);

    void delete(int id);

    Student select(int id);

    List<Student> selectAll();

}
