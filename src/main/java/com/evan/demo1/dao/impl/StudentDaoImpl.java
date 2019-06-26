package com.evan.demo1.dao.impl;

import com.evan.demo1.dao.StudentDao;
import com.evan.demo1.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Student student) {
        String sql = "insert into student(name,sex,born) values(?,?,?)";
        jdbcTemplate.update(sql,student.getName(),student.getSex(),student.getBorn());
    }

    public void update(Student student) {
        String sql = "update student set name=?,sex=?,born=? where id=?";
        jdbcTemplate.update(sql,student.getName(),student.getSex(),student.getBorn(),student.getId());
    }

    public void delete(int id) {
        String sql = "delete from student where id=?";
        jdbcTemplate.update(sql,id);
    }

    public Student select(int id) {
        String sql = "select * from student where id = ?";
        return jdbcTemplate.queryForObject(sql,new StudentRowMapper(),id);
    }

    public List<Student> selectAll() {
        String sql = "select * from student where id = ?";
        return jdbcTemplate.query(sql,new StudentRowMapper());
    }

    private class StudentRowMapper implements RowMapper<Student> {

        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student stu = new Student(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("sex"),
                    resultSet.getDate("born"));
            return stu;
        }
    }
}
