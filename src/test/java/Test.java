import com.evan.demo1.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {

    private JdbcTemplate jdbcTemplate;
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    }

    @org.junit.Test
    public void testExcute(){
        jdbcTemplate.execute("create table user1(id int,name varchar (20))");
    }

    @org.junit.Test
    public void testUpdate(){
        String sql = "insert into student(name, sex)values(?,?)";
        jdbcTemplate.update(sql,new Object[]{"kobe","M"});
    }

    @org.junit.Test
    public void testUpdate2(){
        String sql = "update student set sex=? where id=?";
        jdbcTemplate.update(sql,new Object[]{"M","1001"});
    }

    @org.junit.Test
    public void testBatchUpdate(){
        String[] sqls = {
                "insert into student(name,sex)values('关羽','女')",
                "insert into student(name,sex)values('刘备','男')",
                "update student set sex = '女' where id=1001"
        };
        jdbcTemplate.batchUpdate(sqls);
    }

    @org.junit.Test
    public void testBstchUpdate2(){
        String sql = "insert into selection(student,course) value(?,?)";
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {1003,1001});
        list.add(new Object[] {1003,1003});
        jdbcTemplate.batchUpdate(sql,list);
    }

    @org.junit.Test
    public void testQuerySimple(){
        String sql = "select count(*) from student";
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println(count);
    }

    @org.junit.Test
    public void testQerySimple2(){
        String sql = "select name from student where sex=?";
        List<String> names = jdbcTemplate.queryForList(sql,String.class,"女");
        System.out.println(names);
    }

    @org.junit.Test
    public void testQueryMap1(){
        String sql = "select * from student where id = ?";
        Map<String,Object> stu = jdbcTemplate.queryForMap(sql, 1003);
        System.out.println(stu);
    }

    @org.junit.Test
    public void testQueryMap2(){
        String sql = "select * from student";
        List<Map<String,Object>> stu = jdbcTemplate.queryForList(sql);
        System.out.println(stu);
    }

    @org.junit.Test
    public void testQueryEntity1(){
        String sql = "select * from student where id = ?";
        Student student = jdbcTemplate.queryForObject(sql,  new StudentRowMapper(),1003);
        System.out.println(student);
    }

    @org.junit.Test
    public void testQureEntity2(){
        String sql = "select * from student";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
        System.out.println(students);
    }

    private class StudentRowMapper implements RowMapper<Student>{

        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student stu = new Student(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("sex"),
                    resultSet.getDate("born"));
            return stu;
        }
    }

}
