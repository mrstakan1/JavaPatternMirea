package ru.example.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.entities.Student;
import ru.example.services.StudentService;
import ru.example.entities.Subject;
import ru.example.services.SubjectService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final SubjectService subjectService;

    @GetMapping("/search/{name}")
    public List<Subject> getSpecificSubject(@PathVariable String name) {
        return subjectService.getSpecificSubject(name);
    }

    @GetMapping("/students")
    public List<Student> getStudents() throws SQLException {
        return studentService.getStudents();
    }

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student) throws SQLException {
        return studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable int id) throws SQLException {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/subjects")
    public List<Subject> getSubjects() throws SQLException {
        return subjectService.getSubjects();
    }

    @PostMapping("/add-subject")
    public Subject addSubject(@RequestBody Subject subject) throws SQLException {
        return subjectService.addSubject(subject);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteSubject(@PathVariable int id) throws SQLException {
        return subjectService.deleteSubject(id);
    }
}
