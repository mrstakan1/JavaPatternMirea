package ru.example.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.entities.Student;
import ru.example.entities.Subject;
import ru.example.repositories.SubjectRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {
    private SubjectRepository subjectRepository;

    public List<Subject> getSubjects() throws SQLException {
        return subjectRepository.getSubjects();
    }
    public Subject addSubject(Subject subject) throws SQLException {
        return subjectRepository.addSubject(subject);
    }

    public boolean deleteSubject(int id) throws SQLException {
        return subjectRepository.deleteSubject(id);
    }

    public List<Subject> getSpecificSubject(String name) {
        return subjectRepository.getSpecificSubject(name);
    }
}
