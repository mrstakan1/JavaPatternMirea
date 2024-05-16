package ru.example;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.example.entities.Student;
import ru.example.entities.Subject;
import ru.example.repo.StudentRepository;
import ru.example.repo.SubjectRepository;


@org.springframework.stereotype.Service
@AllArgsConstructor
@Slf4j
public class Service {
    private StudentRepository studentRepository;
    private SubjectRepository subjectRepository;

    public Iterable<Student> getStudents() {
        log.info("!!!  All students  !!!");
        return studentRepository.findAll();
    }
    public Student addStudent(Student student) {
        log.info("!!!  Add student  !!!");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        log.info("!!!  Delete student  !!!");
        studentRepository.deleteById(id);
    }

    public Iterable<Subject> getSubjects() {
        log.info("!!!  All subjects  !!!");
        return subjectRepository.findAll();
    }

    public Subject addSubject(Subject subject) {
        log.info("!!!  Add subject  !!!");
        return subjectRepository.save(subject);
    }

    public void deleteSubject(long id) {
        log.info("!!!  Delete subject  !!!");
        subjectRepository.deleteById(id);
    }

    public Iterable<Subject> getSpecificSubject(String name) {
        log.info("!!!  Get subject by name  !!!");
        return subjectRepository.findAllByName(name);
    }
}
