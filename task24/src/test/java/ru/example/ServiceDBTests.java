package ru.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tuganov.entities.Student;
import ru.tuganov.entities.Subject;

import ru.example.repo.StudentRepository;
import ru.example.repo.SubjectRepository;
import ru.example.services.ServiceDB;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ServiceDBTests {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private ServiceDB serviceDB;

    @Test
    public void getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Doe", "Smith", null));
        students.add(new Student(2, "Jane", "Doe", "Jones", null));

        Mockito.when(studentRepository.findAll()).thenReturn(students);

        Iterable<Student> studentIterable = serviceDB.getStudents();
        List<Student> studentList = new ArrayList<>();
        studentIterable.forEach(studentList::add);

        assertEquals(2, studentList.size());
    }

    @Test
    public void addStudent() {
        Student student = new Student(1, "John", "Doe", "Smith", null);

        Mockito.when(studentRepository.save(student)).thenReturn(student);

        assertEquals(student, serviceDB.addStudent(student));
    }

    @Test
    public void deleteStudent() {
        long studentId = 1;

        Mockito.doNothing().when(studentRepository).deleteById(studentId);

        Assertions.assertDoesNotThrow(() -> serviceDB.deleteStudent(studentId));
    }

    @Test
    public void getSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1, "Math", "Advanced Calculus", 1));
        subjects.add(new Subject(2, "Physics", "Quantum Mechanics", 1));

        Mockito.when(subjectRepository.findAll()).thenReturn(subjects);

        Iterable<Subject> subjectIterable = serviceDB.getSubjects();
        List<Subject> subjectList = new ArrayList<>();
        subjectIterable.forEach(subjectList::add);

        assertEquals(2, subjectList.size());
    }

    @Test
    public void addSubject() {
        Subject subject = new Subject(1, "Math", "Advanced Calculus", 1);

        Mockito.when(subjectRepository.save(subject)).thenReturn(subject);

        assertEquals(subject, serviceDB.addSubject(subject));
    }

    @Test
    public void deleteSubject() {
        long subjectId = 1;

        Mockito.doNothing().when(subjectRepository).deleteById(subjectId);

        Assertions.assertDoesNotThrow(() -> serviceDB.deleteSubject(subjectId));
    }

    @Test
    public void getSpecificSubject() {
        String subjectName = "Math";
        Subject subject = new Subject(1, "Math", "Advanced Calculus", 1);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);

        Mockito.when(serviceDB.getSpecificSubject(subjectName)).thenReturn(subjects);

        List<Subject> result = (List<Subject>) serviceDB.getSpecificSubject(subjectName);

        assertEquals(1, result.size());
        assertEquals(subject, result.getFirst());
    }
}

