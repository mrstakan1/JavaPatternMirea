package ru.example.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.example.entities.Subject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class SubjectRepository {
    private final DataSource ds;
    public List<Subject> getSubjects() throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from subjects");
        ResultSet rs = ps.executeQuery();
        List<Subject> subjects = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String description = rs.getString(3);
            int student_id = rs.getInt(4);
            subjects.add(new Subject(id, name, description, student_id));
        }
        return subjects;
    }

    public Subject addSubject(Subject subject) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into subjects (id, name, description, student_id) values (?, ?, ?, ?)", new String[] {"id"});
        ps.setInt(1, subject.getId());
        ps.setString(2, subject.getName());
        ps.setString(3, subject.getDescription());
        ps.setInt(4, subject.getStudent_id());
        ps.executeUpdate();
        return subject;
    }

    public boolean deleteSubject(int id) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement ps = connection.prepareStatement("delete from subjects where id = ?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}
