package ru.example.task15;

import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StudentRepository {
    private final DataSource ds;

    public void init() throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS students (" +
                        "  id INT PRIMARY KEY," +
                        "  first_name VARCHAR(100) NOT NULL," +
                        "  second_name VARCHAR(100) NOT NULL," +
                        "  middle_name VARCHAR(100) NOT NULL" +
                        ")"
        );
        ps.execute();
    }

    public List<Student> getStudents() throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from students");
        ResultSet rs = ps.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String first_name = rs.getString(2);
            String second_name = rs.getString(3);
            String middle_name = rs.getString(4);
            students.add(new Student(id, first_name, second_name, middle_name));
        }
        return students;
    }

    public Student addStudent(Student student) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into students (id, first_name, second_name, middle_name) values (?, ?, ?, ?)", new String[] {"id"});
        ps.setInt(1, student.getId());
        ps.setString(2, student.getFirst_name());
        ps.setString(3, student.getSecond_name());
        ps.setString(4, student.getMiddle_name());
        ps.executeUpdate();
        return student;
    }

    public boolean deleteStudent(int id) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("delete from students where id = ?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}
