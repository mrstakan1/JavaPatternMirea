package ru.example;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.entities.Student;
import ru.example.entities.Subject;
import ru.example.services.ServiceDB;

@RestController
@AllArgsConstructor
public class Controller {
    private final ServiceDB serviceDB;

    @GetMapping("/search/{name}")
    public Iterable<Subject> getSpecificSubject(@PathVariable String name) {
        return serviceDB.getSpecificSubject(name);
    }

    @GetMapping("/students")
    public Iterable<Student> getStudents() {
        return serviceDB.getStudents();
    }

    @PostMapping("/add-student")
    public Student addStudent(@RequestBody Student student) {
        return serviceDB.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        serviceDB.deleteStudent(id);
    }

    @GetMapping("/subjects")
    public Iterable<Subject> getSubjects() {
        return serviceDB.getSubjects();
    }

    @PostMapping("/add-subject")
    public Subject addSubject(@RequestBody Subject subject) {
        return serviceDB.addSubject(subject);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSubject(@PathVariable int id) {
        serviceDB.deleteSubject(id);
    }
}
