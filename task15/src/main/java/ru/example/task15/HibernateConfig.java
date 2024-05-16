package ru.example.task15;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
