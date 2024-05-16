package ru.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Student {
    @Id
    private int id;
    private String first_name;
    private String second_name;
    private String middle_name;
    @OneToMany
    private List<Subject> subjectList;

    public Student() {

    }
}
