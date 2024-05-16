package ru.example.task15;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;


    @GetMapping("/")
    public String start() {
        return "index";
    }

    @GetMapping("/students")
    @ResponseBody
    public List<Student> getStudents() throws SQLException {
        return service.getStudents();
    }

    @PostMapping("/add-student")
    @ResponseBody
    public Student addStudent(@RequestBody Student student) throws SQLException {
        return service.addStudent(student);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public boolean deleteStudent(@PathVariable int id) throws SQLException {
        return service.deleteStudent(id);
    }
}
