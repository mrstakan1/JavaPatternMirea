package ru.example;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.example.entities.Student;
import ru.example.entities.Subject;
import ru.example.repo.StudentRepository;
import ru.example.repo.SubjectRepository;


@org.springframework.stereotype.Service
@AllArgsConstructor
@Slf4j
@Transactional
public class Service {
    private StudentRepository studentRepository;
    private SubjectRepository subjectRepository;
    private CustomEmailSender customEmailSender;

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
        customEmailSender.sendEmail(subject);
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
