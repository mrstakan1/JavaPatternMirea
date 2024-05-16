package ru.example.task14;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UniversityController {
    private List<University> universityList = new ArrayList<>();

    @PostMapping("/add-univ")
    public University createStudent(@RequestBody University university) {
        universityList.add(university);
        return university;
    }

    @GetMapping("/univs")
    public List<University> getAllStudents() {
        return universityList;
    }

    @DeleteMapping("/univs/{index}")
    public University deleteStudent(@PathVariable int index) {
        if (index >= 0 && index < universityList.size()) {
            return universityList.remove(index);
        } else {
            return null;
        }
    }
}
