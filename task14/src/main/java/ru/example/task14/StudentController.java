package ru.example.task14;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    private List<Student> students = new ArrayList<>();

    @GetMapping("/")
    public String start() {
        return "index";
    }

    @PostMapping("/add-student")
    @ResponseBody
    public Student createStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @GetMapping("/students")
    @ResponseBody
    public List<Student> getAllStudents() {
        return students;
    }

    @DeleteMapping("/students/{index}")
    @ResponseBody
    public Student deleteStudent(@PathVariable int index) {
        if (index >= 0 && index < students.size()) {
            return students.remove(index);
        } else {
            return null;
        }
    }
}
