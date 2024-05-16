package ru.example.task15;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
    @Id
    private int id;
    private String first_name;
    private String second_name;
    private String middle_name;

    public Student(int id, String first_name, String second_name, String middle_name) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
    }

    public Student() {

    }
}
