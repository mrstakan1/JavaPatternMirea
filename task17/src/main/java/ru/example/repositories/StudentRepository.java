package ru.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.example.entities.Student;
import ru.example.entities.Subject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentRepository {
    private final DataSource ds;

    public StudentRepository(DataSource ds) {
        this.ds = ds;
    }

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
        PreparedStatement ps2 = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS subjects (" +
                        "id INT PRIMARY KEY," +
                        "name VARCHAR(100)," +
                        "description VARCHAR(100)," +
                        "student_id INT," +
                        "FOREIGN KEY (student_id) REFERENCES students(id)" +
                        ")"
        );
        ps2.execute();
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

            PreparedStatement psCourses = connection.prepareStatement("SELECT * FROM subjects WHERE student_id = ?");
            psCourses.setInt(1, id);
            ResultSet rs2 = psCourses.executeQuery();
            List<Subject> subjects = new ArrayList<>();

            while (rs2.next()) {
                int idSubject = rs2.getInt(1);
                String nameSubject = rs2.getString(2);
                String description = rs2.getString(2);
                subjects.add(new Subject(idSubject, nameSubject, description, id));
            }
            students.add(new Student(id, first_name, second_name, middle_name, subjects));
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

        for (Subject subject : student.getSubjectList()) {
            PreparedStatement psSubject = connection.prepareStatement("INSERT INTO subjects (id, name, description, student_id) VALUES (?, ?, ?, ?)");
            psSubject.setInt(1, student.getId());
            psSubject.setString(2, subject.getName());
            psSubject.setString(3, subject.getDescription());
            psSubject.setInt(4, student.getId());
            psSubject.executeUpdate();
        }

        return student;
    }

    public boolean deleteStudent(int id) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("delete from students where id = ?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}
