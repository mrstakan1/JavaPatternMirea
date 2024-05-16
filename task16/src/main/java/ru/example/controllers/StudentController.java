package ru.example.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.example.entities.Student;
import ru.example.entities.Subject;
import ru.example.services.StudentService;
import ru.example.services.SubjectService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final SubjectService subjectService;


    @GetMapping("/")
    public String start() {
        return "index";
    }

    @GetMapping("/students")
    @ResponseBody
    public List<Student> getStudents() throws SQLException {
        return studentService.getStudents();
    }

    @PostMapping("/add-student")
    @ResponseBody
    public Student addStudent(@RequestBody Student student) throws SQLException {
        return studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public boolean deleteStudent(@PathVariable int id) throws SQLException {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/subjects")
    @ResponseBody
    public List<Subject> getSubjects() throws SQLException {
        return subjectService.getSubjects();
    }

    @PostMapping("/add-subject")
    @ResponseBody
    public Subject addSubject(@RequestBody Subject subject) throws SQLException {
        return subjectService.addSubject(subject);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public boolean deleteSubject(@PathVariable int id) throws SQLException {
        return subjectService.deleteSubject(id);
    }
}
