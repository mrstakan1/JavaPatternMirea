package ru.example.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomUser {
    private String name;
    private String password;
//    private List<String> authorities;
}