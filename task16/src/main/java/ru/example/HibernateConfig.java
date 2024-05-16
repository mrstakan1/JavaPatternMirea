package ru.example;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.example.repositories.StudentRepository;
import ru.example.repositories.SubjectRepository;
import ru.example.services.StudentService;
import ru.example.services.SubjectService;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@AllArgsConstructor
public class HibernateConfig {
    private final DataSource ds;
    @Bean
    public StudentService studentService() throws SQLException {
        return new StudentService(studentRest());
    }

    @Bean
    public StudentRepository studentRest() throws SQLException {
        StudentRepository studentRepository = new StudentRepository(ds);
        studentRepository.init();
        return studentRepository;
    }

    @Bean
    public SubjectService subjectService() throws SQLException {
        return new SubjectService(subjectRest());
    }

    @Bean
    public SubjectRepository subjectRest() throws SQLException {
        return new SubjectRepository(ds);
    }
}
