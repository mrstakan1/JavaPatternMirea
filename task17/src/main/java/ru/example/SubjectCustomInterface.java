package ru.example;

import ru.example.entities.Subject;

import java.util.List;

public interface SubjectCustomInterface {
    List<Subject> getSpecificSubject(String name);
}
